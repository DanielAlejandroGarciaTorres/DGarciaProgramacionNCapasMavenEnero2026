package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Pais;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Result;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PaisDAOImplementation implements IPais {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    @Override
    public Result GetAll() {

        Result result = new Result();

        try {

            // aqui llamo a jdbcTemplate
            result.objects = new ArrayList<>();
            result.objects.add(new Pais(1, "México"));
            result.objects.add(new Pais(21, "España"));
            result.objects.add(new Pais(22, "Rusia"));
            result.objects.add(new Pais(23, "Colombia"));

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
