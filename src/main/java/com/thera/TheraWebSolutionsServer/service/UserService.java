package com.thera.TheraWebSolutionsServer.service;

import com.thera.TheraWebSolutionsServer.model.User;
import com.thera.TheraWebSolutionsServer.service.core.ServiceCore;
import com.thera.TheraWebSolutionsServer.service.filters.UserFilter;

public interface UserService extends ServiceCore<User,Integer, UserFilter> {
}
