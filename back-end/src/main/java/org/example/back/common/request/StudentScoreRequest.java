package org.example.back.common.request;

import lombok.Data;
import org.example.back.common.Term;

/**
 * @author Canary
 * @version 1.0.0
 * @title StudentScoreRequst
 * @description
 * @creat 2024/11/10 上午11:14
 **/
@Data
public class StudentScoreRequest {
    private String className;
    private Term term;
}
