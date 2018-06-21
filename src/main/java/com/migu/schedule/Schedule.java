package com.migu.schedule;

import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*
*类名和方法不能修改
 */
public class Schedule {
	private final Set<Integer> nodes = new TreeSet<>();
	private final Map<Integer, TaskItem> tasks = new TreeMap<>();
	private final Set<Integer> pending = new HashSet<>();

	private TaskItem assignTask(int consumption, int nodeId) {
		for(Map.Entry<Integer, TaskItem> entry: tasks.entrySet()) {
		    TaskItem item = entry.getValue();
			if(-1 == item.getNodeId() && item.getConsumption() == consumption) {
				item.setNodeId(nodeId);
				return item;
			}
		}
		return null;
	}
	
	
	public int init() {
		nodes.clear();
		tasks.clear();
		pending.clear();
		return ReturnCodeKeys.E001;
	}

	

	public int registerNode(int nodeId) {
		if (nodeId > 0) {
			if (nodes.contains(nodeId)) {
				return ReturnCodeKeys.E005;
			} else {
				nodes.add(nodeId);
				return ReturnCodeKeys.E003;
			}
		} else {
			return ReturnCodeKeys.E004;
		}
	}

	

	public int unregisterNode(int nodeId) {
		if (nodeId > 0) {
			return (nodes.remove(nodeId)) ? ReturnCodeKeys.E006 : ReturnCodeKeys.E007;
		} else {
			return ReturnCodeKeys.E004;
		}
	}

	
	public int addTask(int taskId, int consumption) {

		if (taskId > 0 && consumption > 0) {
			if (!tasks.containsKey(taskId)) {
				tasks.put(taskId, new TaskItem(taskId, consumption));
				pending.add(taskId);
				return ReturnCodeKeys.E008;
			} else {
				return ReturnCodeKeys.E010;
			}
		} else {
			return ReturnCodeKeys.E009;
		}
	}

	
	public int deleteTask(int taskId) {
		if (taskId > 0) {
			if (tasks.containsKey(taskId)) {
				tasks.remove(taskId);
				pending.remove(taskId);
				return ReturnCodeKeys.E011;
			} else {
				return ReturnCodeKeys.E012;
			}
		} else {
			return ReturnCodeKeys.E009;
		}
	}

	
	public int scheduleTask(int threshold) {
	    // TODO 方法未实现
	        return ReturnCodeKeys.E000;
	}

	

	public int queryTaskStatus(List<TaskInfo> result) {
		if (null != result) {
			result.clear();
			for (TaskItem item : tasks.values()) {
				TaskInfo task = new TaskInfo();
				task.setNodeId(item.getNodeId());
				task.setTaskId(item.getTaskId());
				result.add(task);
			}
			return ReturnCodeKeys.E015;
		} else {
			return ReturnCodeKeys.E016;
		}
	}

}