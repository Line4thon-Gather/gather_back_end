package org.example.gather_back_end.view.service;

import java.util.List;
import org.example.gather_back_end.creator.dto.filtering.CreatorInfo;

public interface ViewService {
    void execute(String providerId, String nickname);
    List<CreatorInfo> getViewCreatorList(String providerId);
}
