package com.settlemint.LoginAssessmentApplication.service;

import com.settlemint.LoginAssessmentApplication.model.Employee;
import com.settlemint.LoginAssessmentApplication.model.Manager;
import com.settlemint.LoginAssessmentApplication.repository.ManagerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ManagerServiceTest {

    @Autowired
    private ManagerService managerService;


    @Test
    public void UpdateManagerTest()
    {

        managerService.saveManager(Manager.builder().id(12L).teams("IT").name("Lawrence").build());
        System.out.println(managerService.FetchAllManager());
        System.out.println(managerService.updateManager(Manager.builder().id(12L).name("Lawrence").teams("HR").build()));
    }

    @Test
    public void NoUpdateManagerTest()
    {

        assertEquals(null,managerService.updateManager(Manager.builder().id(15L).name("Lawrence").teams("HR").build()));
    }

    @Test
    public void deleteManagerTest()
    {
        Manager savedManager= managerService.saveManager(Manager.builder().id(1300L).teams("Noteams").name("Nomanager").build());
        System.out.println(managerService.FetchAllManager());
        assertEquals(savedManager.getId()+" Id got deleted",managerService.deleteManager(savedManager.getId()));
    }

    @Test
    void fetchManagerList() {

        List<Manager> mlist=managerService.FetchAllManager();
        System.out.println(mlist);
        assertNotNull(mlist);
    }
}