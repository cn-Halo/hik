package com.hik.dao;

import com.hik.entity.FaceAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaceAttendanceDao extends JpaRepository<FaceAttendance, Long> {
}
