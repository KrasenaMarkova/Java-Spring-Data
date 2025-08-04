package bg.softuni._16_softunigamestore.service;

import bg.softuni._16_softunigamestore.dto.*;

import java.util.Set;

public interface GameService {
    String addGame(GameCreateDto gameCreateDto);

    String editGame(GameEditDto gameEditDto);

    String deleteGame(int id);

    Set<GameViewDto> getAllGames();

    GameDetailDto getDetailGame(String title);

    String getOwnedGame();

    String addItem(String token);

    String removeItem(String token);

    String buyItem(String token);
}
