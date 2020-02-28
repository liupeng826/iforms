package com.microastudio.iforms.modules.form.dto;

import com.microastudio.iforms.modules.system.domain.Dept;
import com.microastudio.iforms.modules.system.domain.User;
import lombok.Data;

import java.io.Serializable;

/**
 * @author peng
 */
@Data
public class DealerDto implements Serializable {

    private static final long serialVersionUID = -2100368029465319722L;

    private Dept dept;

    private User user;

}
