import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Erwin on 24/03/16.
 */
@RestController
public class MyRestController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;



    @RequestMapping(value="/history", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public String getHistory() {

        return null;


    }
}