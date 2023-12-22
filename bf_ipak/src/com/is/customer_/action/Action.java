package com.is.customer_.action;

import lombok.Data;
import lombok.ToString;

/**
 * Created by root on 05.06.2017.
 * 15:39
 */
@Data
@ToString
public class Action {
    private int deal_id;
    private int action_id;
    private String name;
    private int state_begin;
    private int state_end;
}
