package com.migu.schedule;

import java.util.LinkedList;
import java.util.List;

public class NodeInfo {
 
	 int nodeId;
	List<Integer> consumptions = new LinkedList<>();
	int total = 0;

	NodeInfo(int nodeId) {
		this.nodeId = nodeId;
		this.total = 0;
	}

	void addTask(TaskItem task) {
		consumptions.add(task.consumption);
		//tasks.put(task.taskId, task.consumption);
		total += task.consumption;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getNodeId() {
		return nodeId;
	}

	public List<Integer> getConsumptions() {
		return consumptions;
	}
	
	@Override
	public String toString() {
		return "NodeInfo [" + nodeId + ", " + total + "]";
	}

}

