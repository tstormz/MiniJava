package com.tstorm.compiler.rules.expressions;

import com.tstorm.compiler.rules.Type;

/**
 * Created by tstorm on 11/1/16.
 */
public interface Expression {

    Type resolveToType();

}
