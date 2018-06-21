package com.migu.schedule;

public class TaskItem {
	int nodeId;
	int taskId;
	int consumption;

	TaskItem(int taskId, int consumption) {
		this.nodeId = -1;
		this.taskId = taskId;
		this.consumption = consumption;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public int getNodeId() {
		return nodeId;
	}

	public int getConsumption() {
		return consumption;
	}

	public void setConsumption(int consumption) {
		this.consumption = consumption;
	}

	@Override
	public String toString() {
		return "TaskItem [" + taskId + ", " + consumption + ", " + nodeId + "]";
	}
}
