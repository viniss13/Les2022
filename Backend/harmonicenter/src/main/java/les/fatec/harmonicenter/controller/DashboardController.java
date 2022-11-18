package les.fatec.harmonicenter.controller;

import les.fatec.harmonicenter.DTO.DashboardDTO;
import les.fatec.harmonicenter.DTO.ProductDTO;
import les.fatec.harmonicenter.DTO.ProductDTO2;
import les.fatec.harmonicenter.domain.Client;
import les.fatec.harmonicenter.domain.Product;
import les.fatec.harmonicenter.domain.Result;
import les.fatec.harmonicenter.facade.Facade;
import les.fatec.harmonicenter.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Tuple;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/dashboard")
public class DashboardController {


    @Autowired
    DashboardRepository dashboardRepository;

    @GetMapping("/read")
    public ResponseEntity read(){

//        List<Object> result = dashboardRepository.findDashboard();
//        System.out.println(result.toString());
//
//
//        return ResponseEntity.ok().body(result);

        List<DashboardDTO> result = dashboardRepository.getDashboard();

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/read_by_date")
    public ResponseEntity read_by_date(){
        Client client = new Client();
        LocalDate ld = client.getCreationDate();

        return ResponseEntity.ok().body(null);
    }
    //

}
