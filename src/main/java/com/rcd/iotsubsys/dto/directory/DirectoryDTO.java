package com.rcd.iotsubsys.dto.directory;

import com.rcd.iotsubsys.domain.directory.DirectoryNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-07 13:15
 */
public class DirectoryDTO {
    private DirectoryNode cur;
    private List<DirectoryDTO> child;

    public DirectoryDTO() {
        this.child = new ArrayList<>();
    }

    public DirectoryNode getCur() {
        return cur;
    }

    public void setCur(DirectoryNode cur) {
        this.cur = cur;
    }

    public List<DirectoryDTO> getChild() {
        return child;
    }

    public void setChild(List<DirectoryDTO> child) {
        this.child = child;
    }
}
