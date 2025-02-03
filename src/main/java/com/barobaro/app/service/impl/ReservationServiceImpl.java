package com.barobaro.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barobaro.app.mapper.ReservationMapper;
import com.barobaro.app.service.ReservationService;


@Service("reservationServiceImpl")
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	private ReservationMapper reservationMapper;

	// 대여희망자가 예약을 요청
	@Override
	public int processReservation(long timeSlotSeq) {
			       reservationMapper.requestReservation(timeSlotSeq);
		int rows = reservationMapper.updateStatusUnavailable(timeSlotSeq);
		return rows;
	}
	
	// 물품등록자가 예약을 수락
	@Override
	public int acceptReservation(long reservationSeq) {
		return reservationMapper.acceptReservation(reservationSeq);
	}
	
	// 물품등록자가 예약을 거절
	@Override
	public int processRefuseReservation(long reservationSeq) {
			       reservationMapper.refuseReservation(reservationSeq);
		int rows = reservationMapper.updateStatusAvailableByReservationSeq(reservationSeq);
		return rows;
	}
	
	// 대여희망자가 예약을 취소요청
	@Override
	public int cancleRequest(long reservationSeq) {
		return reservationMapper.cancleRequest(reservationSeq);
	}
	
	// 물품등록자가 예약 취소요청을 수락
	@Override
	public int processCancleAccept(long reservationSeq) {
				   reservationMapper.cancleAccept(reservationSeq);
		int rows = reservationMapper.updateStatusAvailableByReservationSeq(reservationSeq);
		return rows;
	}

	// 거래 완료됨
	@Override
	public int done(long reservationSeq) {
		return reservationMapper.done(reservationSeq);
	}
	

}
