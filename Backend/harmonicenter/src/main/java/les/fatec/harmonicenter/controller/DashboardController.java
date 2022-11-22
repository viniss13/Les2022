package les.fatec.harmonicenter.controller;

import les.fatec.harmonicenter.DTO.DashboardDTO;
import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.repository.DashboardRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/dashboard")
public class DashboardController {


    @Autowired
    DashboardRepository dashboardRepository;

    @GetMapping("/read")
    public ResponseEntity read(){

        List<DashboardDTO> result = dashboardRepository.getDashboard();

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/read_by_date")
    public ResponseEntity read_by_date(@Param("startDate") String startDate, @Param("endDate") String endDate){



        List<DashboardDTO> result = dashboardRepository.getDashboardByDate(LocalDate.parse(startDate), LocalDate.parse(endDate));

        return ResponseEntity.ok().body(result);
    }

}
