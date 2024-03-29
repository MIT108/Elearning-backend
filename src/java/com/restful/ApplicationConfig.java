/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restful;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author miend
 */
@javax.ws.rs.ApplicationPath("v1")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.controller.AdminController.class);
        resources.add(com.controller.AuthController.class);
        resources.add(com.controller.ClassController.class);
        resources.add(com.controller.CourseController.class);
        resources.add(com.controller.MessageController.class);
    }
    
}
