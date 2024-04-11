package com.bobocode.checklist.service;

import com.bobocode.checklist.entity.CheckList;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ChecklistService {

    private final Map<Long, CheckList> taskListByIdMap = new ConcurrentHashMap<>();
    private final AtomicLong listIdGenerator = new AtomicLong(0L);
    private final AtomicLong taskIdGenerator = new AtomicLong(0L);

    public CheckList saveList(CheckList checkList) {
        Long generatedId = listIdGenerator.incrementAndGet();
        checkList.getTasks()
                .forEach(task -> task.setId(taskIdGenerator.incrementAndGet()));
        taskListByIdMap.put(generatedId, checkList);

        return checkList;
    }
}
