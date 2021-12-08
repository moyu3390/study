package com.nijunyang.tx.tcc.stock.controller;

import com.nijunyang.tx.common.dto.StorageDTO;
import com.nijunyang.tx.tcc.stock.service.StockService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description:
 * Created by nijunyang on 2021/12/8 21:47
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Resource
    private StockService stockService;

    @PostMapping("/decrease")
    public Boolean decrease(@RequestBody StorageDTO storageDTO) {
        return stockService.decrease(storageDTO);
    }
}
