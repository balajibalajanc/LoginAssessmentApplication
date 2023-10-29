package com.settlemint.LoginAssessmentApplication.service;

import com.settlemint.LoginAssessmentApplication.model.Manager;
import com.settlemint.LoginAssessmentApplication.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    public Manager saveManager(Manager manager)
    {
        return managerRepository.save(manager);
    }

    public List<Manager> FetchAllManager()
    {
        return managerRepository.findAll();
    }

    public Manager updateManager(Manager manager)
    {
        return managerRepository.findById(manager.getId())
                .map(foundManager -> {
                    if (manager.getTeams() != null) {
                        foundManager.setTeams(manager.getTeams());
                    }
                    if (manager.getName() != null) {
                        foundManager.setName(manager.getName());
                    }
                    return managerRepository.save(foundManager);
                })
                .orElse(null);
    }

    public String deleteManager(Long managerId)
    {
        Optional<Manager> optionalManager = managerRepository.findById(managerId);
        if(optionalManager.isPresent())
        {
             managerRepository.deleteById(managerId);
            return managerId+" "+ "Id got deleted";
        }
        return "No Manager found with the manger Id:"+ " "+managerId;
    }
}
