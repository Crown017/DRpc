package com.crown.servicecommon.annotaion;


import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DRpcService {
    Class value();
}
