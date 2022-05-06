package com.virtusa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.helpdesk.models.Ticket;
import com.hr.helpdesk.models.User;
import com.hr.helpdesk.repository.TicketRepository;

@Service
public class TicketService {

	@Autowired private TicketRepository repo;
	
	public void saveTicket(Ticket ticket) {
		ticket.setHandleby(ticket.getCreatedby().getReportto());
		repo.save(ticket);
	}
	
	public void reopenTicket(int id,String subject,String reason) {
		Ticket ticket=findById(id);
		ticket.setStatus("Reopen");
		saveTicket(ticket);
		
		Ticket newticket=new Ticket();
		newticket.setCreatedby(ticket.getCreatedby());
		newticket.setSubject(subject);
		newticket.setDescription(reason);
		newticket.setParentid(ticket);
		saveTicket(newticket);
	}
	
	public List<Ticket> alltickets(){
		return repo.findAll();
	}
	
	public List<Ticket> userTickets(User user){
		return repo.findByCreatedby(user);
	}
	
	public void deleteTicket(int id) {
		repo.delete(repo.getById(id));
	}
	
	public List<Ticket> handlerTickets(User user){
		return repo.findByHandleby(user);
	}
	
	public Ticket findById(int id) {
		return repo.getById(id);
	}
}
