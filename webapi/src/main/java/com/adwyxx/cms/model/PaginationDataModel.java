package com.adwyxx.cms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/12/18 15:03
 */
@Data
@AllArgsConstructor
@ToString
public class PaginationDataModel<T> implements Serializable {

    private static final long serialVersionUID = -4160066792271782139L;

    private List<T> data;
    private Long total;

    public PaginationDataModel()
    {
        this.data = new ArrayList<>();
        this.total = 0L;
    }
}
