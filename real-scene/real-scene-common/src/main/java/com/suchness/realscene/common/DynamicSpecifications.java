package com.suchness.realscene.common;

import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;

/***
 *  author: wch
 *  create_time : 2020/6/12 14:55
 *******/
public class DynamicSpecifications {
    public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters, final Class<T> entityClazz) {
        SimpleSpecification<T> simpleSpecification = new SimpleSpecification<T>(filters);
        return simpleSpecification;
    }
}
