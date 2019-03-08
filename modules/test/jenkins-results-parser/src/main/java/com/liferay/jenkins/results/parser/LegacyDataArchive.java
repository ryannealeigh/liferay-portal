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

package com.liferay.jenkins.results.parser;

import java.io.File;
import java.io.IOException;

import java.util.List;

/**
 * @author Michael Hashimoto
 */
public class LegacyDataArchive {

	public String getDataArchiveType() {
		return _dataArchiveType;
	}

	public File getLegacyDataArchiveFile() {
		return _legacyDataArchiveFile;
	}

	public LegacyDataArchiveUtil getLegacyDataArchiveUtil() {
		return _legacyDataArchiveUtil;
	}

	public GitWorkingDirectory getLegacyGitWorkingDirectory() {
		return _legacyGitWorkingDirectory;
	}

	public LocalGitCommit getLocalGitCommit() {
		if (_legacyDataArchiveFile.exists()) {
			List<LocalGitCommit> localGitCommits =
				_legacyGitWorkingDirectory.log(1, _legacyDataArchiveFile);

			return localGitCommits.get(0);
		}

		return null;
	}

	public boolean isUpdated() {
		LocalGitCommit localGitCommit = getLocalGitCommit();

		if (localGitCommit == null) {
			return false;
		}

		LocalGitCommit latestTestLocalGitCommit =
			_legacyDataArchivePortalVersion.getLatestTestLocalGitCommit();

		String gitCommitMessage = localGitCommit.getMessage();

		if (gitCommitMessage.contains(
				latestTestLocalGitCommit.getAbbreviatedSHA())) {

			return true;
		}

		return false;
	}

	public void stageLegacyDataArchive() throws IOException {
		String dataArchiveType = _legacyDataArchiveGroup.getDataArchiveType();
		File generatedArchiveDirectory =
			_legacyDataArchiveUtil.getGeneratedArchiveDirectory();
		String portalVersion =
			_legacyDataArchivePortalVersion.getPortalVersion();

		File generatedArchiveFile = new File(
			JenkinsResultsParserUtil.combine(
				generatedArchiveDirectory.toString(), "/", portalVersion, "/",
				dataArchiveType, "-", _databaseName, ".zip"));

		if (generatedArchiveFile.exists()) {
			JenkinsResultsParserUtil.copy(
				generatedArchiveFile, _legacyDataArchiveFile);

			_legacyGitWorkingDirectory.stageFileInCurrentLocalGitBranch(
				JenkinsResultsParserUtil.getCanonicalPath(
					_legacyDataArchiveFile));
		}
	}

	protected LegacyDataArchive(
		LegacyDataArchiveGroup legacyDataArchiveGroup, String databaseName) {

		_legacyDataArchiveGroup = legacyDataArchiveGroup;
		_databaseName = databaseName;

		_legacyDataArchivePortalVersion =
			_legacyDataArchiveGroup.getLegacyDataArchivePortalVersion();

		_legacyDataArchiveUtil =
			_legacyDataArchivePortalVersion.getLegacyDataArchiveUtil();

		_legacyGitWorkingDirectory =
			_legacyDataArchiveUtil.getLegacyGitWorkingDirectory();

		_dataArchiveType = _legacyDataArchiveGroup.getDataArchiveType();

		String portalVersion =
			_legacyDataArchivePortalVersion.getPortalVersion();
		File legacyDataWorkingDirectory =
			_legacyGitWorkingDirectory.getWorkingDirectory();

		_legacyDataArchiveFile = new File(
			JenkinsResultsParserUtil.combine(
				legacyDataWorkingDirectory.toString(), "/", portalVersion,
				"/data-archive/", _dataArchiveType, "-", _databaseName,
				".zip"));
	}

	private final String _dataArchiveType;
	private final String _databaseName;
	private final File _legacyDataArchiveFile;
	private final LegacyDataArchiveGroup _legacyDataArchiveGroup;
	private final LegacyDataArchivePortalVersion
		_legacyDataArchivePortalVersion;
	private final LegacyDataArchiveUtil _legacyDataArchiveUtil;
	private final GitWorkingDirectory _legacyGitWorkingDirectory;

}