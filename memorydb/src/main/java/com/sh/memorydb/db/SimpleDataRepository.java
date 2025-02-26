package com.sh.memorydb.db;

import com.sh.memorydb.entity.Entity;

import java.util.*;
import java.util.stream.Collectors;

abstract public class SimpleDataRepository<T extends Entity, ID extends Long> implements DataRepository<T, ID> {
    private List<T> dataList = new ArrayList<T>();

    private static long index = 0;

    private Comparator<T> sort = new Comparator<>() {

        @Override
        public int compare(T o1, T o2) {
            return Long.compare(o1.getId(), o2.getId());
        }
    };

    //create
    @Override
    public T save(T data) {

        if(Objects.isNull(data)) {
            throw new RuntimeException("Data is Null");
        }
        //db에 이미 있는 데이터인가?
        var prevData = dataList.stream()
                .filter(it->{
                    return it.getId().equals(data.getId());
                })
                .findFirst();

        if(prevData.isPresent()) {
            //기존 데이터 있는 경우(업데이트), 해당 데이터 삭제하고 다시 저장(아이디는 유지)
            //Optional<UserEntity>이기에 .get으로 UserEntity를 받아야 함 (prevData는 그냥 Optional)
            dataList.remove(prevData.get());
            dataList.add(data);
        }else{
            //기존 데이터 없는 경우(삽입)
            index++;
            //unique ID
            data.setId(index);
            dataList.add(data);
        }
        
        return data;
    }

    //read
    @Override
    public Optional<T> findById(ID id) {
        return dataList.stream()
                .filter(it->
                {
                    return it.getId().equals(id);
                })
                .findFirst();
    } //data가 있을수도 없을수도 있기에 Optional

    @Override
    public List<T> findAll() {
        return dataList.stream()
                .sorted(sort)
                .collect(Collectors.toList());
    }

    //delete
    @Override
    public void delete(ID id) {
        var deleteEntity = dataList.stream()
                .filter(it->{
                    return it.getId().equals(id);
                })
                .findFirst();
        if(deleteEntity.isPresent()) {
            dataList.remove(deleteEntity.get());
        }
    }


}
