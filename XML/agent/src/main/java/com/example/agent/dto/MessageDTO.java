package com.example.agent.dto;

public class MessageDTO {
	
	protected Long id;
	protected String title;
	protected String content;
	protected  Long clientId;
	protected String clientName;
	protected String clientSurname;
	protected Long agentId;
	protected String agentName;
	protected String agentSurname;
	protected boolean sending;
	public MessageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MessageDTO(Long id, String title, String content, Long clientId,
			String clientName, String clientSurname, Long agentId,
			String agentName, String agentSurname, boolean sending) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.clientId = clientId;
		this.clientName = clientName;
		this.clientSurname = clientSurname;
		this.agentId = agentId;
		this.agentName = agentName;
		this.agentSurname = agentSurname;
		this.sending = sending;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientSurname() {
		return clientSurname;
	}
	public void setClientSurname(String clientSurname) {
		this.clientSurname = clientSurname;
	}
	public Long getAgentId() {
		return agentId;
	}
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentSurname() {
		return agentSurname;
	}
	public void setAgentSurname(String agentSurname) {
		this.agentSurname = agentSurname;
	}
	public boolean isSending() {
		return sending;
	}
	public void setSending(boolean sending) {
		this.sending = sending;
	}
	
	

}
