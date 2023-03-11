package com.dineshvijay.saloonapi.controller;

import com.dineshvijay.saloonapi.service.SlotService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/services")
@CrossOrigin({"http://localhost:3000", "http://192.168.101.5:3000"})
@Api(tags = {"slot selection"})
public class SalonSlotController {

    private final SlotService slotService;

    public SalonSlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @ApiOperation(value = "RetrieveAvailableSlotsAPI")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "salonServiceld",
                    value = "Selected salon service id",
                    required = true,
                    dataType = "int",
                    paramType = "path"),
            @ApiImplicitParam(
                    name = "formattedDate",
                    value = "date in yyyy-MM-dd format",
                    required = true,
                    dataType = "string",
                    defaultValue = "2023-03-18",
                    paramType = "path")
    })
    @GetMapping(path="/retrieveAvailableSlots/{salonServiceld}/{formattedDate}")
    public ResponseEntity<Object> retrieveAvailableSlots(@PathVariable int salonServiceld,
                                                         @PathVariable String formattedDate) {
        return slotService.getAvailableSlots(salonServiceld, formattedDate);
    }

}
