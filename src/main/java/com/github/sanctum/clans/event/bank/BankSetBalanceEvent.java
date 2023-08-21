package com.github.sanctum.clans.event.bank;

import com.github.sanctum.clans.construct.api.ClanBank;
import java.math.BigDecimal;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;

public class BankSetBalanceEvent extends BankEvent implements Cancellable {

    private final BigDecimal newBalance;
    private boolean cancelled = false;

    public BankSetBalanceEvent(@NotNull ClanBank clanBank, @NotNull BigDecimal newBalance) {
        super(clanBank, false);
        this.newBalance = newBalance;
    }

    /**
     * Gets the potential new balance.
     *
     * @return the desired balance as a BigDecimal
     */
    public BigDecimal getNewBalance() {
        return newBalance;
    }

    /**
     * Gets the potential new balance.
     *
     * @return the desired balance as a double
     */
    public double getNewBalanceAsDouble() {
        return newBalance.doubleValue();
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
