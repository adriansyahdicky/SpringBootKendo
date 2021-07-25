package com.apotik.service.history;

import com.apotik.dto.history.HistoryCreateDto;
import com.apotik.entity.History;
import com.apotik.repository.HistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    public List<History> getHistorys(){
        return historyRepository.findAll();
    }

    public void saveHistory(HistoryCreateDto historyCreateDto){
        History history =
                History.builder()
                .namaUser(historyCreateDto.getNamaUser())
                .activity(historyCreateDto.getActivity())
                .tanggalActivity(new Date())
                .build();
        historyRepository.save(history);
    }

}
