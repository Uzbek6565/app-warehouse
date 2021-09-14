package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Client;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public Result addClient(Client client) {
        if (clientRepository.existsByNameAndPhoneNumber(client.getName(), client.getPhoneNumber()))
            return new Result("Client already exists", false);
        clientRepository.save(client);
        return new Result("Client is added", true);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Result getClientById(Integer id) {
        if (clientRepository.existsById(id))
            return new Result("Client with ID: "+ id,true, clientRepository.getById(id));
        return new Result("Client not found", false);
    }

    public Result editClient(Integer id, Client client) {
        if (clientRepository.existsByNameAndPhoneNumber(client.getName(), client.getPhoneNumber()))
            return new Result("Client already exists", false);
        if (!clientRepository.existsById(id))
            return new Result("Client not found", false);
        Client clientById = clientRepository.getById(id);
        clientById.setName(client.getName());
        clientById.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(clientById);
        return new Result("Client data is edited", true);
    }

    public Result deleteClient(Integer id) {
        if (!clientRepository.existsById(id))
            return new Result("Client not found", false);
        clientRepository.deleteById(id);
        return new Result("Client data is edited", true);
    }
}
