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

package com.liferay.digital.signature.model.field.builder;

import aQute.bnd.annotation.ProviderType;

import com.liferay.digital.signature.model.field.DSField;

/**
 * @author Michael C. Han
 */
@ProviderType
public interface UserEntryDSFieldBuilder<T extends DSField<?>>
	extends StyledDSFieldBuilder<T> {

	public <S> S setConcealValue(Boolean concealValue);

	public <S> S setDisableAutoSize(Boolean disableAutoSize);

	public <S> S setMaxLength(Integer maxLength);

	public <S> S setOriginalValue(String originalValue);

	public <S> S setRequireInitialOnSharedChange(
		Boolean requireInitialOnSharedChange);

	public <S> S setValidationMessage(String validationMessage);

	public <S> S setValidationRegex(String validationRegex);

	public <S> S setValue(String value);

}