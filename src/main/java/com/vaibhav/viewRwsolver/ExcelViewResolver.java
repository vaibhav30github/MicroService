package com.vaibhav.viewRwsolver;


import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import com.vaibhav.view.ExcelView;

import java.util.Locale;

/**
 * Created by aboullaite on 2017-02-24.
 */
public class ExcelViewResolver implements ViewResolver {
    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        ExcelView view = new ExcelView();
        return view;
    	}
	}
