package com.hik.dao;

import com.hik.entity.Attendance;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yzm on 2019/8/19.
 */
public interface AttendanceDao extends JpaRepository<Attendance, Long> {

}
