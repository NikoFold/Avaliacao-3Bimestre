package clinica.com.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinica.com.entity.Consulta;
import clinica.com.repository.ConsultaRepository;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository repo;

    public Consulta save(Consulta c) throws Exception { 
    	//CONSULTA NÃO PODE SER CADASTRADA 	NO PASSADO
    	if (c.getDataHora() != null && c.getDataHora().isBefore(LocalDateTime.now())) {
    	    throw new Exception("A data/hora da consulta não pode estar no passado.");
    	}
    	
        return repo.save(c); }
    public List<Consulta> list() { return repo.findAll(); }
    public Consulta findById(Long id) { return repo.findById(id).orElseThrow(); }
    public void deleteById(Long id) { repo.deleteById(id); }
}