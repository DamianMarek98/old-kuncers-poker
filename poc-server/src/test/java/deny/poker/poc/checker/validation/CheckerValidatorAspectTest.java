package deny.poker.poc.checker.validation;

import deny.poker.poc.Card;
import deny.poker.poc.Color;
import deny.poker.poc.Figure;
import deny.poker.poc.checker.SetChecker;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ContextConfiguration(classes = {CheckerValidatorAspectTest.TestConfiguration.class})
@EnableAspectJAutoProxy
class CheckerValidatorAspectTest {
    @org.springframework.boot.test.context.TestConfiguration
    static class TestConfiguration {
        @Bean
        public SetChecker setChecker() {
            return new SetChecker() {
                @Override
                @CheckerValidator
                public boolean contains(List<Card> cards, Figure figure, Color color) {
                    return false;
                }
            };
        }
    }

    @Autowired
    private SetChecker setChecker;

    @Test
    void shouldWorkWithoutWhenNoRequiredFields() {
        assertFalse(setChecker.contains(List.of(), null, null));
    }

    @Nested
    @SpringBootTest
    @ContextConfiguration(classes = {CheckerValidatorWithRequiredFieldsAspectTest.TestConfiguration.class})
    @EnableAspectJAutoProxy
    class CheckerValidatorWithRequiredFieldsAspectTest {
        @org.springframework.boot.test.context.TestConfiguration
        static class TestConfiguration {
            @Bean
            public SetChecker setCheckerWithRequiredFields() {
                return new SetChecker() {
                    @Override
                    @CheckerValidator(figureRequired = true, colorRequired = true)
                    public boolean contains(List<Card> cards, Figure figure, Color color) {
                        return false;
                    }
                };
            }
        }

        @Autowired
        private SetChecker setCheckerWithRequiredFields;

        @Test
        void shouldThrowFigureNotSpecifiedException() {
            assertThrows(FigureNotSpecifiedSetCheckerException.class, () -> setCheckerWithRequiredFields.contains(List.of(), null, null));
        }

        @Test
        void shouldThrowColorNotSpecifiedException() {
            assertThrows(ColorNotSpecifiedSetCheckerException.class, () -> setCheckerWithRequiredFields.contains(List.of(), Figure.JACK, null));
        }

        @Test
        void shouldWorkWithoutException() {
            assertFalse(setCheckerWithRequiredFields.contains(List.of(), Figure.JACK, Color.RED_HEART));
        }
    }
}
