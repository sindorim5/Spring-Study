package com.sindorim.demo.apisecondsample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sindorim.demo.apisecondsamples.controller.PetApi;
import com.sindorim.demo.apisecondsamples.domain.Pet;

@RestController
public class PetControllerImpl implements PetApi {

    @Override
    public ResponseEntity<Pet> getPetById(Long petId) {
        return PetApi.super.getPetById(petId);
    }

}
