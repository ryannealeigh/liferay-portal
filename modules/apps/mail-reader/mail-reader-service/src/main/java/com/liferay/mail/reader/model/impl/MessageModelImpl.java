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

package com.liferay.mail.reader.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.mail.reader.model.Message;
import com.liferay.mail.reader.model.MessageModel;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the Message service. Represents a row in the &quot;Mail_Message&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>MessageModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MessageImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageImpl
 * @generated
 */
@ProviderType
public class MessageModelImpl
	extends BaseModelImpl<Message> implements MessageModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a message model instance should use the <code>Message</code> interface instead.
	 */
	public static final String TABLE_NAME = "Mail_Message";

	public static final Object[][] TABLE_COLUMNS = {
		{"messageId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"accountId", Types.BIGINT}, {"folderId", Types.BIGINT},
		{"sender", Types.VARCHAR}, {"to_", Types.CLOB}, {"cc", Types.CLOB},
		{"bcc", Types.CLOB}, {"sentDate", Types.TIMESTAMP},
		{"subject", Types.VARCHAR}, {"preview", Types.VARCHAR},
		{"body", Types.CLOB}, {"flags", Types.VARCHAR}, {"size_", Types.BIGINT},
		{"remoteMessageId", Types.BIGINT}, {"contentType", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("messageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("accountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("folderId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("sender", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("to_", Types.CLOB);
		TABLE_COLUMNS_MAP.put("cc", Types.CLOB);
		TABLE_COLUMNS_MAP.put("bcc", Types.CLOB);
		TABLE_COLUMNS_MAP.put("sentDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("subject", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("preview", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("body", Types.CLOB);
		TABLE_COLUMNS_MAP.put("flags", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("size_", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("remoteMessageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("contentType", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Mail_Message (messageId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,accountId LONG,folderId LONG,sender STRING null,to_ TEXT null,cc TEXT null,bcc TEXT null,sentDate DATE null,subject STRING null,preview VARCHAR(75) null,body TEXT null,flags VARCHAR(75) null,size_ LONG,remoteMessageId LONG,contentType VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table Mail_Message";

	public static final String ORDER_BY_JPQL = " ORDER BY message.sentDate ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Mail_Message.sentDate ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long FOLDERID_COLUMN_BITMASK = 2L;

	public static final long REMOTEMESSAGEID_COLUMN_BITMASK = 4L;

	public static final long SENTDATE_COLUMN_BITMASK = 8L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	public MessageModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _messageId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setMessageId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _messageId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Message.class;
	}

	@Override
	public String getModelClassName() {
		return Message.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Message, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Message, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Message, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Message)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Message, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Message, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Message)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Message, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Message, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Message>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Message.class.getClassLoader(), Message.class, ModelWrapper.class);

		try {
			Constructor<Message> constructor =
				(Constructor<Message>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<Message, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Message, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Message, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Message, Object>>();
		Map<String, BiConsumer<Message, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Message, ?>>();

		attributeGetterFunctions.put("messageId", Message::getMessageId);
		attributeSetterBiConsumers.put(
			"messageId", (BiConsumer<Message, Long>)Message::setMessageId);
		attributeGetterFunctions.put("companyId", Message::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<Message, Long>)Message::setCompanyId);
		attributeGetterFunctions.put("userId", Message::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Message, Long>)Message::setUserId);
		attributeGetterFunctions.put("userName", Message::getUserName);
		attributeSetterBiConsumers.put(
			"userName", (BiConsumer<Message, String>)Message::setUserName);
		attributeGetterFunctions.put("createDate", Message::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<Message, Date>)Message::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", Message::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<Message, Date>)Message::setModifiedDate);
		attributeGetterFunctions.put("accountId", Message::getAccountId);
		attributeSetterBiConsumers.put(
			"accountId", (BiConsumer<Message, Long>)Message::setAccountId);
		attributeGetterFunctions.put("folderId", Message::getFolderId);
		attributeSetterBiConsumers.put(
			"folderId", (BiConsumer<Message, Long>)Message::setFolderId);
		attributeGetterFunctions.put("sender", Message::getSender);
		attributeSetterBiConsumers.put(
			"sender", (BiConsumer<Message, String>)Message::setSender);
		attributeGetterFunctions.put("to", Message::getTo);
		attributeSetterBiConsumers.put(
			"to", (BiConsumer<Message, String>)Message::setTo);
		attributeGetterFunctions.put("cc", Message::getCc);
		attributeSetterBiConsumers.put(
			"cc", (BiConsumer<Message, String>)Message::setCc);
		attributeGetterFunctions.put("bcc", Message::getBcc);
		attributeSetterBiConsumers.put(
			"bcc", (BiConsumer<Message, String>)Message::setBcc);
		attributeGetterFunctions.put("sentDate", Message::getSentDate);
		attributeSetterBiConsumers.put(
			"sentDate", (BiConsumer<Message, Date>)Message::setSentDate);
		attributeGetterFunctions.put("subject", Message::getSubject);
		attributeSetterBiConsumers.put(
			"subject", (BiConsumer<Message, String>)Message::setSubject);
		attributeGetterFunctions.put("preview", Message::getPreview);
		attributeSetterBiConsumers.put(
			"preview", (BiConsumer<Message, String>)Message::setPreview);
		attributeGetterFunctions.put("body", Message::getBody);
		attributeSetterBiConsumers.put(
			"body", (BiConsumer<Message, String>)Message::setBody);
		attributeGetterFunctions.put("flags", Message::getFlags);
		attributeSetterBiConsumers.put(
			"flags", (BiConsumer<Message, String>)Message::setFlags);
		attributeGetterFunctions.put("size", Message::getSize);
		attributeSetterBiConsumers.put(
			"size", (BiConsumer<Message, Long>)Message::setSize);
		attributeGetterFunctions.put(
			"remoteMessageId", Message::getRemoteMessageId);
		attributeSetterBiConsumers.put(
			"remoteMessageId",
			(BiConsumer<Message, Long>)Message::setRemoteMessageId);
		attributeGetterFunctions.put("contentType", Message::getContentType);
		attributeSetterBiConsumers.put(
			"contentType",
			(BiConsumer<Message, String>)Message::setContentType);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getMessageId() {
		return _messageId;
	}

	@Override
	public void setMessageId(long messageId) {
		_messageId = messageId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getAccountId() {
		return _accountId;
	}

	@Override
	public void setAccountId(long accountId) {
		_accountId = accountId;
	}

	@Override
	public long getFolderId() {
		return _folderId;
	}

	@Override
	public void setFolderId(long folderId) {
		_columnBitmask |= FOLDERID_COLUMN_BITMASK;

		if (!_setOriginalFolderId) {
			_setOriginalFolderId = true;

			_originalFolderId = _folderId;
		}

		_folderId = folderId;
	}

	public long getOriginalFolderId() {
		return _originalFolderId;
	}

	@Override
	public String getSender() {
		if (_sender == null) {
			return "";
		}
		else {
			return _sender;
		}
	}

	@Override
	public void setSender(String sender) {
		_sender = sender;
	}

	@Override
	public String getTo() {
		if (_to == null) {
			return "";
		}
		else {
			return _to;
		}
	}

	@Override
	public void setTo(String to) {
		_to = to;
	}

	@Override
	public String getCc() {
		if (_cc == null) {
			return "";
		}
		else {
			return _cc;
		}
	}

	@Override
	public void setCc(String cc) {
		_cc = cc;
	}

	@Override
	public String getBcc() {
		if (_bcc == null) {
			return "";
		}
		else {
			return _bcc;
		}
	}

	@Override
	public void setBcc(String bcc) {
		_bcc = bcc;
	}

	@Override
	public Date getSentDate() {
		return _sentDate;
	}

	@Override
	public void setSentDate(Date sentDate) {
		_columnBitmask = -1L;

		_sentDate = sentDate;
	}

	@Override
	public String getSubject() {
		if (_subject == null) {
			return "";
		}
		else {
			return _subject;
		}
	}

	@Override
	public void setSubject(String subject) {
		_subject = subject;
	}

	@Override
	public String getPreview() {
		if (_preview == null) {
			return "";
		}
		else {
			return _preview;
		}
	}

	@Override
	public void setPreview(String preview) {
		_preview = preview;
	}

	@Override
	public String getBody() {
		if (_body == null) {
			return "";
		}
		else {
			return _body;
		}
	}

	@Override
	public void setBody(String body) {
		_body = body;
	}

	@Override
	public String getFlags() {
		if (_flags == null) {
			return "";
		}
		else {
			return _flags;
		}
	}

	@Override
	public void setFlags(String flags) {
		_flags = flags;
	}

	@Override
	public long getSize() {
		return _size;
	}

	@Override
	public void setSize(long size) {
		_size = size;
	}

	@Override
	public long getRemoteMessageId() {
		return _remoteMessageId;
	}

	@Override
	public void setRemoteMessageId(long remoteMessageId) {
		_columnBitmask |= REMOTEMESSAGEID_COLUMN_BITMASK;

		if (!_setOriginalRemoteMessageId) {
			_setOriginalRemoteMessageId = true;

			_originalRemoteMessageId = _remoteMessageId;
		}

		_remoteMessageId = remoteMessageId;
	}

	public long getOriginalRemoteMessageId() {
		return _originalRemoteMessageId;
	}

	@Override
	public String getContentType() {
		if (_contentType == null) {
			return "";
		}
		else {
			return _contentType;
		}
	}

	@Override
	public void setContentType(String contentType) {
		_contentType = contentType;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Message.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Message toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Message>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		MessageImpl messageImpl = new MessageImpl();

		messageImpl.setMessageId(getMessageId());
		messageImpl.setCompanyId(getCompanyId());
		messageImpl.setUserId(getUserId());
		messageImpl.setUserName(getUserName());
		messageImpl.setCreateDate(getCreateDate());
		messageImpl.setModifiedDate(getModifiedDate());
		messageImpl.setAccountId(getAccountId());
		messageImpl.setFolderId(getFolderId());
		messageImpl.setSender(getSender());
		messageImpl.setTo(getTo());
		messageImpl.setCc(getCc());
		messageImpl.setBcc(getBcc());
		messageImpl.setSentDate(getSentDate());
		messageImpl.setSubject(getSubject());
		messageImpl.setPreview(getPreview());
		messageImpl.setBody(getBody());
		messageImpl.setFlags(getFlags());
		messageImpl.setSize(getSize());
		messageImpl.setRemoteMessageId(getRemoteMessageId());
		messageImpl.setContentType(getContentType());

		messageImpl.resetOriginalValues();

		return messageImpl;
	}

	@Override
	public int compareTo(Message message) {
		int value = 0;

		value = DateUtil.compareTo(getSentDate(), message.getSentDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Message)) {
			return false;
		}

		Message message = (Message)obj;

		long primaryKey = message.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		MessageModelImpl messageModelImpl = this;

		messageModelImpl._originalCompanyId = messageModelImpl._companyId;

		messageModelImpl._setOriginalCompanyId = false;

		messageModelImpl._setModifiedDate = false;

		messageModelImpl._originalFolderId = messageModelImpl._folderId;

		messageModelImpl._setOriginalFolderId = false;

		messageModelImpl._originalRemoteMessageId =
			messageModelImpl._remoteMessageId;

		messageModelImpl._setOriginalRemoteMessageId = false;

		messageModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Message> toCacheModel() {
		MessageCacheModel messageCacheModel = new MessageCacheModel();

		messageCacheModel.messageId = getMessageId();

		messageCacheModel.companyId = getCompanyId();

		messageCacheModel.userId = getUserId();

		messageCacheModel.userName = getUserName();

		String userName = messageCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			messageCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			messageCacheModel.createDate = createDate.getTime();
		}
		else {
			messageCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			messageCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			messageCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		messageCacheModel.accountId = getAccountId();

		messageCacheModel.folderId = getFolderId();

		messageCacheModel.sender = getSender();

		String sender = messageCacheModel.sender;

		if ((sender != null) && (sender.length() == 0)) {
			messageCacheModel.sender = null;
		}

		messageCacheModel.to = getTo();

		String to = messageCacheModel.to;

		if ((to != null) && (to.length() == 0)) {
			messageCacheModel.to = null;
		}

		messageCacheModel.cc = getCc();

		String cc = messageCacheModel.cc;

		if ((cc != null) && (cc.length() == 0)) {
			messageCacheModel.cc = null;
		}

		messageCacheModel.bcc = getBcc();

		String bcc = messageCacheModel.bcc;

		if ((bcc != null) && (bcc.length() == 0)) {
			messageCacheModel.bcc = null;
		}

		Date sentDate = getSentDate();

		if (sentDate != null) {
			messageCacheModel.sentDate = sentDate.getTime();
		}
		else {
			messageCacheModel.sentDate = Long.MIN_VALUE;
		}

		messageCacheModel.subject = getSubject();

		String subject = messageCacheModel.subject;

		if ((subject != null) && (subject.length() == 0)) {
			messageCacheModel.subject = null;
		}

		messageCacheModel.preview = getPreview();

		String preview = messageCacheModel.preview;

		if ((preview != null) && (preview.length() == 0)) {
			messageCacheModel.preview = null;
		}

		messageCacheModel.body = getBody();

		String body = messageCacheModel.body;

		if ((body != null) && (body.length() == 0)) {
			messageCacheModel.body = null;
		}

		messageCacheModel.flags = getFlags();

		String flags = messageCacheModel.flags;

		if ((flags != null) && (flags.length() == 0)) {
			messageCacheModel.flags = null;
		}

		messageCacheModel.size = getSize();

		messageCacheModel.remoteMessageId = getRemoteMessageId();

		messageCacheModel.contentType = getContentType();

		String contentType = messageCacheModel.contentType;

		if ((contentType != null) && (contentType.length() == 0)) {
			messageCacheModel.contentType = null;
		}

		return messageCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Message, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Message, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Message, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Message)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<Message, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Message, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Message, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Message)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Message>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _messageId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _accountId;
	private long _folderId;
	private long _originalFolderId;
	private boolean _setOriginalFolderId;
	private String _sender;
	private String _to;
	private String _cc;
	private String _bcc;
	private Date _sentDate;
	private String _subject;
	private String _preview;
	private String _body;
	private String _flags;
	private long _size;
	private long _remoteMessageId;
	private long _originalRemoteMessageId;
	private boolean _setOriginalRemoteMessageId;
	private String _contentType;
	private long _columnBitmask;
	private Message _escapedModel;

}