package com.adwyxx.cms.repositories;

import com.adwyxx.cms.entities.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2019/3/13 10:45
 */
@Repository
public interface AttachmentRespository  extends JpaRepository<Attachment, String>, JpaSpecificationExecutor, Serializable {
}
