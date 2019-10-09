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

package com.liferay.portal.workflow.kaleo.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.workflow.kaleo.exception.NoSuchTransitionException;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTransitionPersistence;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the kaleo transition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KaleoTransitionPersistenceImpl
	extends BasePersistenceImpl<KaleoTransition>
	implements KaleoTransitionPersistence {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>KaleoTransitionUtil</code> to access the kaleo transition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		KaleoTransitionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the kaleo transitions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo transitions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @return the range of matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo transitions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<KaleoTransition> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the kaleo transitions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<KaleoTransition> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCompanyId;
				finderArgs = new Object[] {companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCompanyId;
			finderArgs = new Object[] {
				companyId, start, end, orderByComparator
			};
		}

		List<KaleoTransition> list = null;

		if (useFinderCache) {
			list = (List<KaleoTransition>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (KaleoTransition kaleoTransition : list) {
					if (companyId != kaleoTransition.getCompanyId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<KaleoTransition>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo transition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition findByCompanyId_First(
			long companyId,
			OrderByComparator<KaleoTransition> orderByComparator)
		throws NoSuchTransitionException {

		KaleoTransition kaleoTransition = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (kaleoTransition != null) {
			return kaleoTransition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchTransitionException(msg.toString());
	}

	/**
	 * Returns the first kaleo transition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByCompanyId_First(
		long companyId, OrderByComparator<KaleoTransition> orderByComparator) {

		List<KaleoTransition> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo transition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition findByCompanyId_Last(
			long companyId,
			OrderByComparator<KaleoTransition> orderByComparator)
		throws NoSuchTransitionException {

		KaleoTransition kaleoTransition = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (kaleoTransition != null) {
			return kaleoTransition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchTransitionException(msg.toString());
	}

	/**
	 * Returns the last kaleo transition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByCompanyId_Last(
		long companyId, OrderByComparator<KaleoTransition> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<KaleoTransition> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo transitions before and after the current kaleo transition in the ordered set where companyId = &#63;.
	 *
	 * @param kaleoTransitionId the primary key of the current kaleo transition
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo transition
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	@Override
	public KaleoTransition[] findByCompanyId_PrevAndNext(
			long kaleoTransitionId, long companyId,
			OrderByComparator<KaleoTransition> orderByComparator)
		throws NoSuchTransitionException {

		KaleoTransition kaleoTransition = findByPrimaryKey(kaleoTransitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoTransition[] array = new KaleoTransitionImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, kaleoTransition, companyId, orderByComparator, true);

			array[1] = kaleoTransition;

			array[2] = getByCompanyId_PrevAndNext(
				session, kaleoTransition, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTransition getByCompanyId_PrevAndNext(
		Session session, KaleoTransition kaleoTransition, long companyId,
		OrderByComparator<KaleoTransition> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						kaleoTransition)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<KaleoTransition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo transitions where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (KaleoTransition kaleoTransition :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(kaleoTransition);
		}
	}

	/**
	 * Returns the number of kaleo transitions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo transitions
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTRANSITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"kaleoTransition.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByKaleoDefinitionId;
	private FinderPath _finderPathWithoutPaginationFindByKaleoDefinitionId;
	private FinderPath _finderPathCountByKaleoDefinitionId;

	/**
	 * Returns all the kaleo transitions where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByKaleoDefinitionId(
		long kaleoDefinitionId) {

		return findByKaleoDefinitionId(
			kaleoDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo transitions where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @return the range of matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) {

		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo transitions where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator<KaleoTransition> orderByComparator) {

		return findByKaleoDefinitionId(
			kaleoDefinitionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the kaleo transitions where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator<KaleoTransition> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByKaleoDefinitionId;
				finderArgs = new Object[] {kaleoDefinitionId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByKaleoDefinitionId;
			finderArgs = new Object[] {
				kaleoDefinitionId, start, end, orderByComparator
			};
		}

		List<KaleoTransition> list = null;

		if (useFinderCache) {
			list = (List<KaleoTransition>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (KaleoTransition kaleoTransition : list) {
					if (kaleoDefinitionId !=
							kaleoTransition.getKaleoDefinitionId()) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoTransition>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition findByKaleoDefinitionId_First(
			long kaleoDefinitionId,
			OrderByComparator<KaleoTransition> orderByComparator)
		throws NoSuchTransitionException {

		KaleoTransition kaleoTransition = fetchByKaleoDefinitionId_First(
			kaleoDefinitionId, orderByComparator);

		if (kaleoTransition != null) {
			return kaleoTransition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append("}");

		throw new NoSuchTransitionException(msg.toString());
	}

	/**
	 * Returns the first kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		OrderByComparator<KaleoTransition> orderByComparator) {

		List<KaleoTransition> list = findByKaleoDefinitionId(
			kaleoDefinitionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition findByKaleoDefinitionId_Last(
			long kaleoDefinitionId,
			OrderByComparator<KaleoTransition> orderByComparator)
		throws NoSuchTransitionException {

		KaleoTransition kaleoTransition = fetchByKaleoDefinitionId_Last(
			kaleoDefinitionId, orderByComparator);

		if (kaleoTransition != null) {
			return kaleoTransition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append("}");

		throw new NoSuchTransitionException(msg.toString());
	}

	/**
	 * Returns the last kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		OrderByComparator<KaleoTransition> orderByComparator) {

		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		if (count == 0) {
			return null;
		}

		List<KaleoTransition> list = findByKaleoDefinitionId(
			kaleoDefinitionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo transitions before and after the current kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoTransitionId the primary key of the current kaleo transition
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo transition
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	@Override
	public KaleoTransition[] findByKaleoDefinitionId_PrevAndNext(
			long kaleoTransitionId, long kaleoDefinitionId,
			OrderByComparator<KaleoTransition> orderByComparator)
		throws NoSuchTransitionException {

		KaleoTransition kaleoTransition = findByPrimaryKey(kaleoTransitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoTransition[] array = new KaleoTransitionImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(
				session, kaleoTransition, kaleoDefinitionId, orderByComparator,
				true);

			array[1] = kaleoTransition;

			array[2] = getByKaleoDefinitionId_PrevAndNext(
				session, kaleoTransition, kaleoDefinitionId, orderByComparator,
				false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTransition getByKaleoDefinitionId_PrevAndNext(
		Session session, KaleoTransition kaleoTransition,
		long kaleoDefinitionId,
		OrderByComparator<KaleoTransition> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

		query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						kaleoTransition)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<KaleoTransition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo transitions where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 */
	@Override
	public void removeByKaleoDefinitionId(long kaleoDefinitionId) {
		for (KaleoTransition kaleoTransition :
				findByKaleoDefinitionId(
					kaleoDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(kaleoTransition);
		}
	}

	/**
	 * Returns the number of kaleo transitions where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo transitions
	 */
	@Override
	public int countByKaleoDefinitionId(long kaleoDefinitionId) {
		FinderPath finderPath = _finderPathCountByKaleoDefinitionId;

		Object[] finderArgs = new Object[] {kaleoDefinitionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTRANSITION_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String
		_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
			"kaleoTransition.kaleoDefinitionId = ?";

	private FinderPath _finderPathWithPaginationFindByKaleoNodeId;
	private FinderPath _finderPathWithoutPaginationFindByKaleoNodeId;
	private FinderPath _finderPathCountByKaleoNodeId;

	/**
	 * Returns all the kaleo transitions where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @return the matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByKaleoNodeId(long kaleoNodeId) {
		return findByKaleoNodeId(
			kaleoNodeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo transitions where kaleoNodeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @return the range of matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByKaleoNodeId(
		long kaleoNodeId, int start, int end) {

		return findByKaleoNodeId(kaleoNodeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo transitions where kaleoNodeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByKaleoNodeId(
		long kaleoNodeId, int start, int end,
		OrderByComparator<KaleoTransition> orderByComparator) {

		return findByKaleoNodeId(
			kaleoNodeId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the kaleo transitions where kaleoNodeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByKaleoNodeId(
		long kaleoNodeId, int start, int end,
		OrderByComparator<KaleoTransition> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByKaleoNodeId;
				finderArgs = new Object[] {kaleoNodeId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByKaleoNodeId;
			finderArgs = new Object[] {
				kaleoNodeId, start, end, orderByComparator
			};
		}

		List<KaleoTransition> list = null;

		if (useFinderCache) {
			list = (List<KaleoTransition>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (KaleoTransition kaleoTransition : list) {
					if (kaleoNodeId != kaleoTransition.getKaleoNodeId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

			query.append(_FINDER_COLUMN_KALEONODEID_KALEONODEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				list = (List<KaleoTransition>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo transition in the ordered set where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition findByKaleoNodeId_First(
			long kaleoNodeId,
			OrderByComparator<KaleoTransition> orderByComparator)
		throws NoSuchTransitionException {

		KaleoTransition kaleoTransition = fetchByKaleoNodeId_First(
			kaleoNodeId, orderByComparator);

		if (kaleoTransition != null) {
			return kaleoTransition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoNodeId=");
		msg.append(kaleoNodeId);

		msg.append("}");

		throw new NoSuchTransitionException(msg.toString());
	}

	/**
	 * Returns the first kaleo transition in the ordered set where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByKaleoNodeId_First(
		long kaleoNodeId,
		OrderByComparator<KaleoTransition> orderByComparator) {

		List<KaleoTransition> list = findByKaleoNodeId(
			kaleoNodeId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo transition in the ordered set where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition findByKaleoNodeId_Last(
			long kaleoNodeId,
			OrderByComparator<KaleoTransition> orderByComparator)
		throws NoSuchTransitionException {

		KaleoTransition kaleoTransition = fetchByKaleoNodeId_Last(
			kaleoNodeId, orderByComparator);

		if (kaleoTransition != null) {
			return kaleoTransition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoNodeId=");
		msg.append(kaleoNodeId);

		msg.append("}");

		throw new NoSuchTransitionException(msg.toString());
	}

	/**
	 * Returns the last kaleo transition in the ordered set where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByKaleoNodeId_Last(
		long kaleoNodeId,
		OrderByComparator<KaleoTransition> orderByComparator) {

		int count = countByKaleoNodeId(kaleoNodeId);

		if (count == 0) {
			return null;
		}

		List<KaleoTransition> list = findByKaleoNodeId(
			kaleoNodeId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo transitions before and after the current kaleo transition in the ordered set where kaleoNodeId = &#63;.
	 *
	 * @param kaleoTransitionId the primary key of the current kaleo transition
	 * @param kaleoNodeId the kaleo node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo transition
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	@Override
	public KaleoTransition[] findByKaleoNodeId_PrevAndNext(
			long kaleoTransitionId, long kaleoNodeId,
			OrderByComparator<KaleoTransition> orderByComparator)
		throws NoSuchTransitionException {

		KaleoTransition kaleoTransition = findByPrimaryKey(kaleoTransitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoTransition[] array = new KaleoTransitionImpl[3];

			array[0] = getByKaleoNodeId_PrevAndNext(
				session, kaleoTransition, kaleoNodeId, orderByComparator, true);

			array[1] = kaleoTransition;

			array[2] = getByKaleoNodeId_PrevAndNext(
				session, kaleoTransition, kaleoNodeId, orderByComparator,
				false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTransition getByKaleoNodeId_PrevAndNext(
		Session session, KaleoTransition kaleoTransition, long kaleoNodeId,
		OrderByComparator<KaleoTransition> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

		query.append(_FINDER_COLUMN_KALEONODEID_KALEONODEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoNodeId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						kaleoTransition)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<KaleoTransition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo transitions where kaleoNodeId = &#63; from the database.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 */
	@Override
	public void removeByKaleoNodeId(long kaleoNodeId) {
		for (KaleoTransition kaleoTransition :
				findByKaleoNodeId(
					kaleoNodeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(kaleoTransition);
		}
	}

	/**
	 * Returns the number of kaleo transitions where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @return the number of matching kaleo transitions
	 */
	@Override
	public int countByKaleoNodeId(long kaleoNodeId) {
		FinderPath finderPath = _finderPathCountByKaleoNodeId;

		Object[] finderArgs = new Object[] {kaleoNodeId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTRANSITION_WHERE);

			query.append(_FINDER_COLUMN_KALEONODEID_KALEONODEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_KALEONODEID_KALEONODEID_2 =
		"kaleoTransition.kaleoNodeId = ?";

	private FinderPath _finderPathFetchByKNI_N;
	private FinderPath _finderPathCountByKNI_N;

	/**
	 * Returns the kaleo transition where kaleoNodeId = &#63; and name = &#63; or throws a <code>NoSuchTransitionException</code> if it could not be found.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param name the name
	 * @return the matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition findByKNI_N(long kaleoNodeId, String name)
		throws NoSuchTransitionException {

		KaleoTransition kaleoTransition = fetchByKNI_N(kaleoNodeId, name);

		if (kaleoTransition == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoNodeId=");
			msg.append(kaleoNodeId);

			msg.append(", name=");
			msg.append(name);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTransitionException(msg.toString());
		}

		return kaleoTransition;
	}

	/**
	 * Returns the kaleo transition where kaleoNodeId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param name the name
	 * @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByKNI_N(long kaleoNodeId, String name) {
		return fetchByKNI_N(kaleoNodeId, name, true);
	}

	/**
	 * Returns the kaleo transition where kaleoNodeId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByKNI_N(
		long kaleoNodeId, String name, boolean useFinderCache) {

		name = Objects.toString(name, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {kaleoNodeId, name};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByKNI_N, finderArgs, this);
		}

		if (result instanceof KaleoTransition) {
			KaleoTransition kaleoTransition = (KaleoTransition)result;

			if ((kaleoNodeId != kaleoTransition.getKaleoNodeId()) ||
				!Objects.equals(name, kaleoTransition.getName())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

			query.append(_FINDER_COLUMN_KNI_N_KALEONODEID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_KNI_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_KNI_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				if (bindName) {
					qPos.add(name);
				}

				List<KaleoTransition> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByKNI_N, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {kaleoNodeId, name};
							}

							_log.warn(
								"KaleoTransitionPersistenceImpl.fetchByKNI_N(long, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					KaleoTransition kaleoTransition = list.get(0);

					result = kaleoTransition;

					cacheResult(kaleoTransition);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByKNI_N, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (KaleoTransition)result;
		}
	}

	/**
	 * Removes the kaleo transition where kaleoNodeId = &#63; and name = &#63; from the database.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param name the name
	 * @return the kaleo transition that was removed
	 */
	@Override
	public KaleoTransition removeByKNI_N(long kaleoNodeId, String name)
		throws NoSuchTransitionException {

		KaleoTransition kaleoTransition = findByKNI_N(kaleoNodeId, name);

		return remove(kaleoTransition);
	}

	/**
	 * Returns the number of kaleo transitions where kaleoNodeId = &#63; and name = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param name the name
	 * @return the number of matching kaleo transitions
	 */
	@Override
	public int countByKNI_N(long kaleoNodeId, String name) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByKNI_N;

		Object[] finderArgs = new Object[] {kaleoNodeId, name};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOTRANSITION_WHERE);

			query.append(_FINDER_COLUMN_KNI_N_KALEONODEID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_KNI_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_KNI_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				if (bindName) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_KNI_N_KALEONODEID_2 =
		"kaleoTransition.kaleoNodeId = ? AND ";

	private static final String _FINDER_COLUMN_KNI_N_NAME_2 =
		"kaleoTransition.name = ?";

	private static final String _FINDER_COLUMN_KNI_N_NAME_3 =
		"(kaleoTransition.name IS NULL OR kaleoTransition.name = '')";

	private FinderPath _finderPathFetchByKNI_DT;
	private FinderPath _finderPathCountByKNI_DT;

	/**
	 * Returns the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; or throws a <code>NoSuchTransitionException</code> if it could not be found.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param defaultTransition the default transition
	 * @return the matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition findByKNI_DT(
			long kaleoNodeId, boolean defaultTransition)
		throws NoSuchTransitionException {

		KaleoTransition kaleoTransition = fetchByKNI_DT(
			kaleoNodeId, defaultTransition);

		if (kaleoTransition == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoNodeId=");
			msg.append(kaleoNodeId);

			msg.append(", defaultTransition=");
			msg.append(defaultTransition);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTransitionException(msg.toString());
		}

		return kaleoTransition;
	}

	/**
	 * Returns the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param defaultTransition the default transition
	 * @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByKNI_DT(
		long kaleoNodeId, boolean defaultTransition) {

		return fetchByKNI_DT(kaleoNodeId, defaultTransition, true);
	}

	/**
	 * Returns the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param defaultTransition the default transition
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByKNI_DT(
		long kaleoNodeId, boolean defaultTransition, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {kaleoNodeId, defaultTransition};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByKNI_DT, finderArgs, this);
		}

		if (result instanceof KaleoTransition) {
			KaleoTransition kaleoTransition = (KaleoTransition)result;

			if ((kaleoNodeId != kaleoTransition.getKaleoNodeId()) ||
				(defaultTransition != kaleoTransition.isDefaultTransition())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

			query.append(_FINDER_COLUMN_KNI_DT_KALEONODEID_2);

			query.append(_FINDER_COLUMN_KNI_DT_DEFAULTTRANSITION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				qPos.add(defaultTransition);

				List<KaleoTransition> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByKNI_DT, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									kaleoNodeId, defaultTransition
								};
							}

							_log.warn(
								"KaleoTransitionPersistenceImpl.fetchByKNI_DT(long, boolean, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					KaleoTransition kaleoTransition = list.get(0);

					result = kaleoTransition;

					cacheResult(kaleoTransition);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByKNI_DT, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (KaleoTransition)result;
		}
	}

	/**
	 * Removes the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; from the database.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param defaultTransition the default transition
	 * @return the kaleo transition that was removed
	 */
	@Override
	public KaleoTransition removeByKNI_DT(
			long kaleoNodeId, boolean defaultTransition)
		throws NoSuchTransitionException {

		KaleoTransition kaleoTransition = findByKNI_DT(
			kaleoNodeId, defaultTransition);

		return remove(kaleoTransition);
	}

	/**
	 * Returns the number of kaleo transitions where kaleoNodeId = &#63; and defaultTransition = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param defaultTransition the default transition
	 * @return the number of matching kaleo transitions
	 */
	@Override
	public int countByKNI_DT(long kaleoNodeId, boolean defaultTransition) {
		FinderPath finderPath = _finderPathCountByKNI_DT;

		Object[] finderArgs = new Object[] {kaleoNodeId, defaultTransition};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOTRANSITION_WHERE);

			query.append(_FINDER_COLUMN_KNI_DT_KALEONODEID_2);

			query.append(_FINDER_COLUMN_KNI_DT_DEFAULTTRANSITION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				qPos.add(defaultTransition);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_KNI_DT_KALEONODEID_2 =
		"kaleoTransition.kaleoNodeId = ? AND ";

	private static final String _FINDER_COLUMN_KNI_DT_DEFAULTTRANSITION_2 =
		"kaleoTransition.defaultTransition = ?";

	public KaleoTransitionPersistenceImpl() {
		setModelClass(KaleoTransition.class);
	}

	/**
	 * Caches the kaleo transition in the entity cache if it is enabled.
	 *
	 * @param kaleoTransition the kaleo transition
	 */
	@Override
	public void cacheResult(KaleoTransition kaleoTransition) {
		entityCache.putResult(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionImpl.class, kaleoTransition.getPrimaryKey(),
			kaleoTransition);

		finderCache.putResult(
			_finderPathFetchByKNI_N,
			new Object[] {
				kaleoTransition.getKaleoNodeId(), kaleoTransition.getName()
			},
			kaleoTransition);

		finderCache.putResult(
			_finderPathFetchByKNI_DT,
			new Object[] {
				kaleoTransition.getKaleoNodeId(),
				kaleoTransition.isDefaultTransition()
			},
			kaleoTransition);

		kaleoTransition.resetOriginalValues();
	}

	/**
	 * Caches the kaleo transitions in the entity cache if it is enabled.
	 *
	 * @param kaleoTransitions the kaleo transitions
	 */
	@Override
	public void cacheResult(List<KaleoTransition> kaleoTransitions) {
		for (KaleoTransition kaleoTransition : kaleoTransitions) {
			if (entityCache.getResult(
					KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
					KaleoTransitionImpl.class,
					kaleoTransition.getPrimaryKey()) == null) {

				cacheResult(kaleoTransition);
			}
			else {
				kaleoTransition.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo transitions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(KaleoTransitionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo transition.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoTransition kaleoTransition) {
		entityCache.removeResult(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionImpl.class, kaleoTransition.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(KaleoTransitionModelImpl)kaleoTransition, true);
	}

	@Override
	public void clearCache(List<KaleoTransition> kaleoTransitions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoTransition kaleoTransition : kaleoTransitions) {
			entityCache.removeResult(
				KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTransitionImpl.class, kaleoTransition.getPrimaryKey());

			clearUniqueFindersCache(
				(KaleoTransitionModelImpl)kaleoTransition, true);
		}
	}

	protected void cacheUniqueFindersCache(
		KaleoTransitionModelImpl kaleoTransitionModelImpl) {

		Object[] args = new Object[] {
			kaleoTransitionModelImpl.getKaleoNodeId(),
			kaleoTransitionModelImpl.getName()
		};

		finderCache.putResult(
			_finderPathCountByKNI_N, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByKNI_N, args, kaleoTransitionModelImpl, false);

		args = new Object[] {
			kaleoTransitionModelImpl.getKaleoNodeId(),
			kaleoTransitionModelImpl.isDefaultTransition()
		};

		finderCache.putResult(
			_finderPathCountByKNI_DT, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByKNI_DT, args, kaleoTransitionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		KaleoTransitionModelImpl kaleoTransitionModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				kaleoTransitionModelImpl.getKaleoNodeId(),
				kaleoTransitionModelImpl.getName()
			};

			finderCache.removeResult(_finderPathCountByKNI_N, args);
			finderCache.removeResult(_finderPathFetchByKNI_N, args);
		}

		if ((kaleoTransitionModelImpl.getColumnBitmask() &
			 _finderPathFetchByKNI_N.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				kaleoTransitionModelImpl.getOriginalKaleoNodeId(),
				kaleoTransitionModelImpl.getOriginalName()
			};

			finderCache.removeResult(_finderPathCountByKNI_N, args);
			finderCache.removeResult(_finderPathFetchByKNI_N, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				kaleoTransitionModelImpl.getKaleoNodeId(),
				kaleoTransitionModelImpl.isDefaultTransition()
			};

			finderCache.removeResult(_finderPathCountByKNI_DT, args);
			finderCache.removeResult(_finderPathFetchByKNI_DT, args);
		}

		if ((kaleoTransitionModelImpl.getColumnBitmask() &
			 _finderPathFetchByKNI_DT.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				kaleoTransitionModelImpl.getOriginalKaleoNodeId(),
				kaleoTransitionModelImpl.getOriginalDefaultTransition()
			};

			finderCache.removeResult(_finderPathCountByKNI_DT, args);
			finderCache.removeResult(_finderPathFetchByKNI_DT, args);
		}
	}

	/**
	 * Creates a new kaleo transition with the primary key. Does not add the kaleo transition to the database.
	 *
	 * @param kaleoTransitionId the primary key for the new kaleo transition
	 * @return the new kaleo transition
	 */
	@Override
	public KaleoTransition create(long kaleoTransitionId) {
		KaleoTransition kaleoTransition = new KaleoTransitionImpl();

		kaleoTransition.setNew(true);
		kaleoTransition.setPrimaryKey(kaleoTransitionId);

		kaleoTransition.setCompanyId(CompanyThreadLocal.getCompanyId());

		return kaleoTransition;
	}

	/**
	 * Removes the kaleo transition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTransitionId the primary key of the kaleo transition
	 * @return the kaleo transition that was removed
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	@Override
	public KaleoTransition remove(long kaleoTransitionId)
		throws NoSuchTransitionException {

		return remove((Serializable)kaleoTransitionId);
	}

	/**
	 * Removes the kaleo transition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo transition
	 * @return the kaleo transition that was removed
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	@Override
	public KaleoTransition remove(Serializable primaryKey)
		throws NoSuchTransitionException {

		Session session = null;

		try {
			session = openSession();

			KaleoTransition kaleoTransition = (KaleoTransition)session.get(
				KaleoTransitionImpl.class, primaryKey);

			if (kaleoTransition == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTransitionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(kaleoTransition);
		}
		catch (NoSuchTransitionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected KaleoTransition removeImpl(KaleoTransition kaleoTransition) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kaleoTransition)) {
				kaleoTransition = (KaleoTransition)session.get(
					KaleoTransitionImpl.class,
					kaleoTransition.getPrimaryKeyObj());
			}

			if (kaleoTransition != null) {
				session.delete(kaleoTransition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kaleoTransition != null) {
			clearCache(kaleoTransition);
		}

		return kaleoTransition;
	}

	@Override
	public KaleoTransition updateImpl(KaleoTransition kaleoTransition) {
		boolean isNew = kaleoTransition.isNew();

		if (!(kaleoTransition instanceof KaleoTransitionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(kaleoTransition.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					kaleoTransition);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in kaleoTransition proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom KaleoTransition implementation " +
					kaleoTransition.getClass());
		}

		KaleoTransitionModelImpl kaleoTransitionModelImpl =
			(KaleoTransitionModelImpl)kaleoTransition;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (kaleoTransition.getCreateDate() == null)) {
			if (serviceContext == null) {
				kaleoTransition.setCreateDate(now);
			}
			else {
				kaleoTransition.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!kaleoTransitionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				kaleoTransition.setModifiedDate(now);
			}
			else {
				kaleoTransition.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (kaleoTransition.isNew()) {
				session.save(kaleoTransition);

				kaleoTransition.setNew(false);
			}
			else {
				kaleoTransition = (KaleoTransition)session.merge(
					kaleoTransition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!KaleoTransitionModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				kaleoTransitionModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByCompanyId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCompanyId, args);

			args = new Object[] {
				kaleoTransitionModelImpl.getKaleoDefinitionId()
			};

			finderCache.removeResult(_finderPathCountByKaleoDefinitionId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByKaleoDefinitionId, args);

			args = new Object[] {kaleoTransitionModelImpl.getKaleoNodeId()};

			finderCache.removeResult(_finderPathCountByKaleoNodeId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByKaleoNodeId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((kaleoTransitionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCompanyId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					kaleoTransitionModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);

				args = new Object[] {kaleoTransitionModelImpl.getCompanyId()};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCompanyId, args);
			}

			if ((kaleoTransitionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByKaleoDefinitionId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					kaleoTransitionModelImpl.getOriginalKaleoDefinitionId()
				};

				finderCache.removeResult(
					_finderPathCountByKaleoDefinitionId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByKaleoDefinitionId, args);

				args = new Object[] {
					kaleoTransitionModelImpl.getKaleoDefinitionId()
				};

				finderCache.removeResult(
					_finderPathCountByKaleoDefinitionId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByKaleoDefinitionId, args);
			}

			if ((kaleoTransitionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByKaleoNodeId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					kaleoTransitionModelImpl.getOriginalKaleoNodeId()
				};

				finderCache.removeResult(_finderPathCountByKaleoNodeId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByKaleoNodeId, args);

				args = new Object[] {kaleoTransitionModelImpl.getKaleoNodeId()};

				finderCache.removeResult(_finderPathCountByKaleoNodeId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByKaleoNodeId, args);
			}
		}

		entityCache.putResult(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionImpl.class, kaleoTransition.getPrimaryKey(),
			kaleoTransition, false);

		clearUniqueFindersCache(kaleoTransitionModelImpl, false);
		cacheUniqueFindersCache(kaleoTransitionModelImpl);

		kaleoTransition.resetOriginalValues();

		return kaleoTransition;
	}

	/**
	 * Returns the kaleo transition with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo transition
	 * @return the kaleo transition
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	@Override
	public KaleoTransition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTransitionException {

		KaleoTransition kaleoTransition = fetchByPrimaryKey(primaryKey);

		if (kaleoTransition == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTransitionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return kaleoTransition;
	}

	/**
	 * Returns the kaleo transition with the primary key or throws a <code>NoSuchTransitionException</code> if it could not be found.
	 *
	 * @param kaleoTransitionId the primary key of the kaleo transition
	 * @return the kaleo transition
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	@Override
	public KaleoTransition findByPrimaryKey(long kaleoTransitionId)
		throws NoSuchTransitionException {

		return findByPrimaryKey((Serializable)kaleoTransitionId);
	}

	/**
	 * Returns the kaleo transition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo transition
	 * @return the kaleo transition, or <code>null</code> if a kaleo transition with the primary key could not be found
	 */
	@Override
	public KaleoTransition fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		KaleoTransition kaleoTransition = (KaleoTransition)serializable;

		if (kaleoTransition == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoTransition = (KaleoTransition)session.get(
					KaleoTransitionImpl.class, primaryKey);

				if (kaleoTransition != null) {
					cacheResult(kaleoTransition);
				}
				else {
					entityCache.putResult(
						KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTransitionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
					KaleoTransitionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kaleoTransition;
	}

	/**
	 * Returns the kaleo transition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoTransitionId the primary key of the kaleo transition
	 * @return the kaleo transition, or <code>null</code> if a kaleo transition with the primary key could not be found
	 */
	@Override
	public KaleoTransition fetchByPrimaryKey(long kaleoTransitionId) {
		return fetchByPrimaryKey((Serializable)kaleoTransitionId);
	}

	@Override
	public Map<Serializable, KaleoTransition> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, KaleoTransition> map =
			new HashMap<Serializable, KaleoTransition>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			KaleoTransition kaleoTransition = fetchByPrimaryKey(primaryKey);

			if (kaleoTransition != null) {
				map.put(primaryKey, kaleoTransition);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTransitionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (KaleoTransition)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_KALEOTRANSITION_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (KaleoTransition kaleoTransition :
					(List<KaleoTransition>)q.list()) {

				map.put(kaleoTransition.getPrimaryKeyObj(), kaleoTransition);

				cacheResult(kaleoTransition);

				uncachedPrimaryKeys.remove(kaleoTransition.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
					KaleoTransitionImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the kaleo transitions.
	 *
	 * @return the kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo transitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @return the range of kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo transitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findAll(
		int start, int end,
		OrderByComparator<KaleoTransition> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the kaleo transitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>KaleoTransitionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findAll(
		int start, int end,
		OrderByComparator<KaleoTransition> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<KaleoTransition> list = null;

		if (useFinderCache) {
			list = (List<KaleoTransition>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_KALEOTRANSITION);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOTRANSITION;

				sql = sql.concat(KaleoTransitionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				list = (List<KaleoTransition>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the kaleo transitions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KaleoTransition kaleoTransition : findAll()) {
			remove(kaleoTransition);
		}
	}

	/**
	 * Returns the number of kaleo transitions.
	 *
	 * @return the number of kaleo transitions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOTRANSITION);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return KaleoTransitionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the kaleo transition persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByCompanyId = new FinderPath(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()},
			KaleoTransitionModelImpl.COMPANYID_COLUMN_BITMASK);

		_finderPathCountByCompanyId = new FinderPath(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByKaleoDefinitionId = new FinderPath(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByKaleoDefinitionId = new FinderPath(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKaleoDefinitionId", new String[] {Long.class.getName()},
			KaleoTransitionModelImpl.KALEODEFINITIONID_COLUMN_BITMASK);

		_finderPathCountByKaleoDefinitionId = new FinderPath(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoDefinitionId", new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByKaleoNodeId = new FinderPath(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByKaleoNodeId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByKaleoNodeId = new FinderPath(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKaleoNodeId",
			new String[] {Long.class.getName()},
			KaleoTransitionModelImpl.KALEONODEID_COLUMN_BITMASK);

		_finderPathCountByKaleoNodeId = new FinderPath(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKaleoNodeId",
			new String[] {Long.class.getName()});

		_finderPathFetchByKNI_N = new FinderPath(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByKNI_N",
			new String[] {Long.class.getName(), String.class.getName()},
			KaleoTransitionModelImpl.KALEONODEID_COLUMN_BITMASK |
			KaleoTransitionModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByKNI_N = new FinderPath(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKNI_N",
			new String[] {Long.class.getName(), String.class.getName()});

		_finderPathFetchByKNI_DT = new FinderPath(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByKNI_DT",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			KaleoTransitionModelImpl.KALEONODEID_COLUMN_BITMASK |
			KaleoTransitionModelImpl.DEFAULTTRANSITION_COLUMN_BITMASK);

		_finderPathCountByKNI_DT = new FinderPath(
			KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKNI_DT",
			new String[] {Long.class.getName(), Boolean.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(KaleoTransitionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_KALEOTRANSITION =
		"SELECT kaleoTransition FROM KaleoTransition kaleoTransition";

	private static final String _SQL_SELECT_KALEOTRANSITION_WHERE_PKS_IN =
		"SELECT kaleoTransition FROM KaleoTransition kaleoTransition WHERE kaleoTransitionId IN (";

	private static final String _SQL_SELECT_KALEOTRANSITION_WHERE =
		"SELECT kaleoTransition FROM KaleoTransition kaleoTransition WHERE ";

	private static final String _SQL_COUNT_KALEOTRANSITION =
		"SELECT COUNT(kaleoTransition) FROM KaleoTransition kaleoTransition";

	private static final String _SQL_COUNT_KALEOTRANSITION_WHERE =
		"SELECT COUNT(kaleoTransition) FROM KaleoTransition kaleoTransition WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTransition.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No KaleoTransition exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No KaleoTransition exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		KaleoTransitionPersistenceImpl.class);

}