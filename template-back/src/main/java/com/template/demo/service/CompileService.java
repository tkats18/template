package com.template.demo.service;

import com.template.demo.model.template.CompileResponse;

public interface CompileService<T> {

    CompileResponse compile (T data);

}
