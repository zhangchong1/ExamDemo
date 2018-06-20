package com.migu;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/*
*类名和方法不能修改
 */
public class Schedule {
	private List<Integer> taskQueue = new ArrayList<Integer>(10);
	//key:计算节点id
	//value index=0:节点中运行的任务数  index=1:节点中最大可运行任务数   >2:taskId
	private Map<Integer,int[]> nodeTaskInfo = new HashMap<Integer,int[]>();
    private Map<Integer,Integer> taskQueueConsumption = new HashMap<Integer,Integer>();
    public int init() {
    	this.taskQueue.clear();
    	this.nodeTaskInfo.clear();
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
        if(nodeId <= 0){
            return ReturnCodeKeys.E004;
        }
        if (this.nodeTaskInfo.containsKey(nodeId)){
            return ReturnCodeKeys.E005;
        }
        int idleResNum = 20;
        this.nodeTaskInfo.put(nodeId, new int[]{0,idleResNum,0});
        return ReturnCodeKeys.E003;
    }

    public int unregisterNode(int nodeId) {
        if(nodeId <= 0){
            return ReturnCodeKeys.E004;
        }
        if (this.nodeTaskInfo.containsKey(nodeId)){
        	this.nodeTaskInfo.remove(nodeId);
        } else {
            return ReturnCodeKeys.E007;
        }
        //查看当前节点是否有任务还在进行，有，将其添加到队列中
        int[] v = nodeTaskInfo.get(nodeId);
        if ( v[0]!= 0){
        	for(int i = 2;i < v.length;i++){
        		if (taskQueue.size()<=10){
        			taskQueue.add(v[i]);
        		}else{
        			System.out.println("达到任务个数最大限度");
        			break;
        		}
        	}
        }
        return ReturnCodeKeys.E006;
    }


    public int addTask(int taskId, int consumption) {
        if(taskId <= 0){
            return ReturnCodeKeys.E009;
        }
        if(taskQueue.contains(taskId)){
            return ReturnCodeKeys.E010;
        }
		if (taskQueue.size()<=10){
			taskQueue.add(taskId);
            taskQueueConsumption.put(taskId,consumption);
            return ReturnCodeKeys.E008;
		}else{
			System.out.println("达到任务个数最大限度");
		}
        return ReturnCodeKeys.E008;
    }


    public int deleteTask(int taskId) {
        if(taskId <= 0){
            return ReturnCodeKeys.E009;
        }
		if (taskQueue.contains(taskId)){
			taskQueue.remove(taskId);
            return ReturnCodeKeys.E011;
		}else{
			System.out.println("删除任务失败");
            return ReturnCodeKeys.E012;
		}								

    }


    public int scheduleTask(int threshold) {
        if(threshold <=0){
            return ReturnCodeKeys.E002;
        }
        return ReturnCodeKeys.E013;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {

        return ReturnCodeKeys.E015;
    }

}
