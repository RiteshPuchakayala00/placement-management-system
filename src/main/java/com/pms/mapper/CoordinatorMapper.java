package com.pms.mapper;

import com.pms.dto.coordinator.CoordinatorProfileResponseDto;
import com.pms.entity.Coordinator;

public class CoordinatorMapper {
    private CoordinatorMapper(){}

    public static CoordinatorProfileResponseDto mapToResponse(Coordinator coordinator){
        if(coordinator == null) return  null ;
        return CoordinatorProfileResponseDto.builder()
                .coordinatorId(coordinator.getCoordinatorId())
                .userId(coordinator.getUser().getUserId())
                .name(coordinator.getUser().getName())
                .email(coordinator.getUser().getEmail())
                .designation(coordinator.getDesignation())
                .build();
    }
}
