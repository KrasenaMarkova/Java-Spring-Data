package bg.softuni._16_softunigamestore.service;

import bg.softuni._16_softunigamestore.dto.*;
import bg.softuni._16_softunigamestore.entities.Game;
import bg.softuni._16_softunigamestore.entities.User;
import bg.softuni._16_softunigamestore.repositories.GameRepository;
import bg.softuni._16_softunigamestore.repositories.Userrepository;
import bg.softuni._16_softunigamestore.utils.ValidatorUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;
    private final Userrepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final UserService userService;

    private final Set<Game> games = new HashSet<>();

    public GameServiceImpl(GameRepository gameRepository, Userrepository userRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil, UserService userService) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.userService = userService;
    }

    @Override
    public String addGame(GameCreateDto gameCreateDto) {
        if(!this.userService.isAdmin()) {
            return "User is not admin";
        }

        if (!this.validatorUtil.isValid(gameCreateDto)) {
            return this.validatorUtil.validate(gameCreateDto)
                    .stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining());
        }
        Game game = this.modelMapper.map(gameCreateDto, Game.class);
        this.gameRepository.saveAndFlush(game);
        return String.format("Added %s", game.getTitle());
    }

    @Override
    public String editGame(GameEditDto gameEditDto) {
        if(!this.userService.isAdmin()) {
            return "User is not admin";
        }

        if (!this.validatorUtil.isValid(gameEditDto)) {
            this.validatorUtil.validate(gameEditDto)
                    .stream()
                    .map(ConstraintViolation ::getMessage)
                    .collect(Collectors.joining());
        }

        Optional<Game> optionalGame = this.gameRepository.findById(gameEditDto.getId());
        if (optionalGame.isEmpty()) {
            return "No such game found";
        }

        Game game = optionalGame.get();

        if (gameEditDto.getPrice() != null) {
            game.setPrice(gameEditDto.getPrice());
        }

        if(gameEditDto.getSize() != null) {
            game.setSize(gameEditDto.getSize());
        }

        this.gameRepository.saveAndFlush(game);
        return String.format("Edited %s", game.getTitle());
    }

    @Override
    public String deleteGame(int id) {
        if(!this.userService.isAdmin()) {
            return "User is not admin";
        }

        Optional<Game> game = this.gameRepository.findById(id);
        if(game.isEmpty()) {
            return "No such game found";
        }
        this.gameRepository.delete(game.get());
        return String.format("Deleted %s", game.get().getTitle());
    }

    @Override
    public Set<GameViewDto> getAllGames() {
        return this.gameRepository.findAll()
                .stream()
                .map(g -> this.modelMapper.map(g, GameViewDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public GameDetailDto getDetailGame(String title) {
        Optional<Game> game = this.gameRepository.findByTitle(title);
        if(game.isEmpty()) {
            return null;
        }
        return this.modelMapper.map(game.get(), GameDetailDto.class);
    }

    @Override
    public String getOwnedGame() {
        if (!this.userService.isLoggedIn()) {
            return "No logged in user";
        }

       return this.userService.getUser()
               .getGames()
               .stream().map(g -> this.modelMapper
                       .map(g, GameOwnedDto.class))
               .map(GameOwnedDto::getTitle)
               .collect(Collectors.joining("\n"));
    }

    @Override
    public String addItem(String token) {
        if (!this.userService.isLoggedIn()) {
            return "No logged in user";
        }
        Optional<Game> game = this.gameRepository.findByTitle(token);
        if (game.isEmpty()) {
            return "No such game found";
        }
        this.games.add(game.get());

        return String.format("%s added to cart", game.get().getTitle());
    }

    @Override
    public String removeItem(String token) {
        if (!this.userService.isLoggedIn()) {
            return "No logged in user";
        }

        Optional<Game> game = this.gameRepository.findByTitle(token);
        if (game.isEmpty()) {
            return "No such game found";
        }

        if (this.games.contains(game.get())) {
            this.games.remove(game.get());
            return String.format("%s removed from cart", game.get().getTitle());
        }
        return String.format("No such game found in card");
    }

    @Override
    public String buyItem(String token) {
        if (!this.userService.isLoggedIn()) {
            return "No logged in user";
        }

        User user = this.userService.getUser();
        Set<Game> newGames = games.stream()
                        .filter(g -> !user.getGames().contains(g))
                                .collect(Collectors.toSet());
        user.getGames().addAll(this.games);
        this.userRepository.saveAndFlush(user);

        // TODO if Games > 0
        return String.format("Successfully bought games:\n\t%s",
                newGames.stream().map(Game::getTitle).collect(Collectors.joining("\n")));
    }
}
