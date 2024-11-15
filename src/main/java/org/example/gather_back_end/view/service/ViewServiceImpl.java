package org.example.gather_back_end.view.service;

import io.swagger.v3.oas.annotations.servers.Server;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.creator.dto.filtering.CreatorInfo;

@Slf4j
@Server
@RequiredArgsConstructor
public class ViewServiceImpl implements ViewService {

    @Override
    public void execute(String providerId, String nickname) {

    }

    @Override
    public List<CreatorInfo> getViewCreatorList(String providerId) {
        return null;
    }
}
