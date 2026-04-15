package selm.customer.entity;


import java.util.UUID;


public record FavouriteProduct (
    UUID id,
    Integer productId
) {
}
