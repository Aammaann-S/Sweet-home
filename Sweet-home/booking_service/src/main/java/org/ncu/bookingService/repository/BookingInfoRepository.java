package org.ncu.bookingService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ncu.bookingService.entities.BookingInfoEntity;

@Repository
public interface BookingInfoRepository extends JpaRepository<BookingInfoEntity, Integer>{

}
