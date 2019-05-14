/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.gradle.plugins.target.platform;

import com.liferay.gradle.plugins.target.platform.extensions.TargetPlatformExtension;
import com.liferay.gradle.plugins.target.platform.internal.util.GradleUtil;
import com.liferay.gradle.plugins.target.platform.internal.util.TargetPlatformPluginUtil;
import com.liferay.gradle.plugins.target.platform.tasks.ResolveTask;

import groovy.lang.Closure;

import io.spring.gradle.dependencymanagement.DependencyManagementPlugin;

import java.io.File;

import java.util.Arrays;
import java.util.Set;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.invocation.Gradle;
import org.gradle.api.logging.Logger;
import org.gradle.api.plugins.JavaBasePlugin;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.PluginContainer;
import org.gradle.api.plugins.PluginManager;
import org.gradle.api.specs.Spec;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.bundling.Jar;

/**
 * @author Gregory Amerson
 * @author Andrea Di Giorgi
 * @author Raymond Augé
 */
public class TargetPlatformPlugin implements Plugin<Project> {

	public static final String PLATFORM_BNDRUN = "platform.bndrun";

	public static final String PLUGIN_NAME = "targetPlatform";

	public static final String RESOLVE_TASK_NAME = "resolve";

	public static final String TARGET_PLATFORM_BOMS_CONFIGURATION_NAME =
		"targetPlatformBoms";

	public static final String TARGET_PLATFORM_DISTRO_CONFIGURATION_NAME =
		"targetPlatformDistro";

	@Override
	@SuppressWarnings("serial")
	public void apply(final Project project) {
		GradleUtil.applyPlugin(project, DependencyManagementPlugin.class);

		final TargetPlatformExtension targetPlatformExtension =
			GradleUtil.addExtension(
				project, PLUGIN_NAME, TargetPlatformExtension.class);

		final Configuration targetPlatformBomsConfiguration =
			_addConfigurationTargetPlatformBoms(project);

		final Configuration targetPlatformDistroConfiguration =
			_addConfigurationTargetPlatformDistro(project);

		PluginContainer pluginContainer = project.getPlugins();

		pluginContainer.withType(
			JavaPlugin.class,
			new Action<JavaPlugin>() {

				@Override
				public void execute(JavaPlugin javaPlugin) {
					TargetPlatformPluginUtil.configureDependencyManagement(
						project, targetPlatformBomsConfiguration,
						_configurationNames);
				}

			});

		Spec<Project> spec = targetPlatformExtension.getOnlyIf();

		Set<Project> subprojects = targetPlatformExtension.getSubprojects();

		for (Project subproject : subprojects) {
			if (spec.isSatisfiedBy(subproject)) {
				GradleUtil.applyPlugin(
					subproject, DependencyManagementPlugin.class);
			}
		}

		Gradle gradle = project.getGradle();

		gradle.afterProject(
			new Closure<Void>(project) {

				@SuppressWarnings("unused")
				public void doCall(Project subproject) {
					if (subprojects.contains(subproject)) {
						_configureSubproject(
							subproject, project.getLogger(),
							targetPlatformBomsConfiguration,
							targetPlatformDistroConfiguration,
							targetPlatformExtension);
					}
				}

			});
	}

	private static Configuration _addConfigurationTargetPlatformBoms(
		Project project) {

		Configuration configuration = GradleUtil.addConfiguration(
			project, TARGET_PLATFORM_BOMS_CONFIGURATION_NAME);

		configuration.setDescription(
			"Configures all the BOMs to import as managed dependencies.");
		configuration.setTransitive(false);
		configuration.setVisible(false);

		return configuration;
	}

	private static Configuration _addConfigurationTargetPlatformDistro(
		Project project) {

		Configuration configuration = GradleUtil.addConfiguration(
			project, TARGET_PLATFORM_DISTRO_CONFIGURATION_NAME);

		configuration.setDescription(
			"Configures the distro JAR file to use as base for resolving " +
				"against.");
		configuration.setTransitive(false);
		configuration.setVisible(false);

		return configuration;
	}

	private ResolveTask _addTaskResolve(Project project) {
		final ResolveTask resolveTask = GradleUtil.addTask(
			project, RESOLVE_TASK_NAME, ResolveTask.class);

		resolveTask.setDescription(
			"Checks whether the project and it is runtime dependencies will " +
				"have their requirements met when installed into the Liferay " +
					"portal instance defined by the configured distro.");
		resolveTask.setGroup(JavaBasePlugin.VERIFICATION_GROUP);

		return resolveTask;
	}

	private void _configureSubproject(
		Project subproject, Logger logger,
		Configuration targetPlatformBomsConfiguration,
		Configuration targetPlatformDistroConfiguration,
		TargetPlatformExtension targetPlatformExtension) {

		TaskContainer taskContainer = subproject.getTasks();

		Task jarTask = taskContainer.findByName(JavaPlugin.JAR_TASK_NAME);

		if (!(jarTask instanceof Jar)) {
			if (logger.isInfoEnabled()) {
				logger.info(
					"Excluding {} because it is not a valid Java project",
					subproject);
			}

			return;
		}

		Spec<Project> spec = targetPlatformExtension.getOnlyIf();

		if (!spec.isSatisfiedBy(subproject)) {
			if (logger.isInfoEnabled()) {
				logger.info("Explicitly excluding {}", subproject);
			}

			return;
		}

		TargetPlatformPluginUtil.configureDependencyManagement(
			subproject, targetPlatformBomsConfiguration, _configurationNames);

		Project rootProject = subproject.getRootProject();

		File bndrunFile = rootProject.file(PLATFORM_BNDRUN);

		if (!bndrunFile.exists()) {
			logger.info(
				"Explicitly excluding {} from resolution because there is no " +
					PLATFORM_BNDRUN +
						" file at the root of the gradle workspace",
				subproject);

			return;
		}

		PluginManager pluginManager = subproject.getPluginManager();

		if (!pluginManager.hasPlugin("com.liferay.osgi.plugin")) {
			logger.info(
				"Explicitly excluding {} from resolution because it does not " +
					"appear to be an OSGi bundle.",
				subproject);

			return;
		}

		Spec<Project> resolveSpec = targetPlatformExtension.getResolveOnlyIf();

		if (resolveSpec.isSatisfiedBy(subproject)) {
			ResolveTask resolveTask = _addTaskResolve(subproject);

			_configureTaskResolve(
				subproject, resolveTask, bndrunFile,
				targetPlatformDistroConfiguration);
		}
		else if (logger.isInfoEnabled()) {
			logger.info("Explicitly excluding {} from resolution", subproject);
		}
	}

	private void _configureTaskResolve(
		Project project, ResolveTask resolveTask, File bndrunFile,
		Configuration targetPlatformDistroConfiguration) {

		TaskContainer taskContainer = project.getTasks();

		resolveTask.dependsOn(taskContainer.findByName("assemble"));

		resolveTask.setDescription(
			"Resolve a project against the Liferay distro");
		resolveTask.setGroup("verification");
		resolveTask.setBndrunFile(bndrunFile);
		resolveTask.setFailOnChanges(false);
		resolveTask.setReportOptional(false);

		resolveTask.setDistro(targetPlatformDistroConfiguration);
	}

	private static final Iterable<String> _configurationNames = Arrays.asList(
		"compile", "compileClasspath", "compileInclude", "compileOnly",
		"default", "implementation", "originalModule", "parentThemes",
		"portalCommonCSS", "runtime", "runtimeClasspath",
		"runtimeImplementation", "runtimeOnly", "testCompileClasspath",
		"testCompileOnly", "testIntegration", "testImplementation",
		"testRuntime", "testRuntimeClasspath", "testRuntimeOnly");

}