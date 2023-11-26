package com.springapps.jpaexamples.coursecompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamService {

    TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public Team addStudentToTeam(User student, Long teamId) throws Exception {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new Exception("team not found"));
        student.setTeam(team);//pune cheia straina la studentul salvat
        team.getParticipants().add(student); //bag student in lista de partcipanti a team
        return teamRepository.save(team);//updatez team si pentru ca am cascadare salveaza student
    }
}
