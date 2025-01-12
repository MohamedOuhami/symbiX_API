package com.v01d.symbiX.dto.Request;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignMemberToTeamRequest {
    
    private Set<Long> membersIds;
}
