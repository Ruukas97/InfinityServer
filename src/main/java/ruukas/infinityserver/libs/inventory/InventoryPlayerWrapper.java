package ruukas.infinityserver.libs.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class InventoryPlayerWrapper implements IInventory
{
    private final InventoryPlayer inv;
    
    public InventoryPlayerWrapper(InventoryPlayer inventory){
        this.inv = inventory;
    }
    
    public InventoryPlayerWrapper(EntityPlayer player){
        this.inv = player.inventory;
    }
    
    public InventoryPlayer getInventory(){
        return this.inv;
    }

    @Override
    public String getName()
    {
        return inv.player.getDisplayNameString() + " - " + inv.getName();
    }

    @Override
    public boolean hasCustomName()
    {
        return inv.hasCustomName();
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return inv.player.getDisplayName().appendText( " - " ).appendSibling( new TextComponentTranslation( inv.getName() ) );
    }

    @Override
    public int getSizeInventory()
    {
        return inv.getSizeInventory();
    }

    @Override
    public boolean isEmpty()
    {
        return inv.isEmpty();
    }

    @Override
    public ItemStack getStackInSlot( int index )
    {
        return inv.getStackInSlot( index );
    }

    @Override
    public ItemStack decrStackSize( int index, int count )
    {
        return inv.decrStackSize( index, count );
    }

    @Override
    public ItemStack removeStackFromSlot( int index )
    {
        return inv.removeStackFromSlot( index );
    }

    @Override
    public void setInventorySlotContents( int index, ItemStack stack )
    {
        inv.setInventorySlotContents( index, stack );
    }

    @Override
    public int getInventoryStackLimit()
    {
        return inv.getInventoryStackLimit();
    }

    @Override
    public void markDirty()
    {
        inv.markDirty();
    }

    @Override
    public boolean isUsableByPlayer( EntityPlayer player )
    {
        //TODO permissions and increase range
        return inv.isUsableByPlayer( player );
    }

    @Override
    public void openInventory( EntityPlayer player )
    {
        inv.openInventory( player );
    }

    @Override
    public void closeInventory( EntityPlayer player )
    {
        inv.openInventory( player );
    }

    @Override
    public boolean isItemValidForSlot( int index, ItemStack stack )
    {
        return inv.isItemValidForSlot( index, stack );
    }

    @Override
    public int getField( int id )
    {
        return inv.getField( id );
    }

    @Override
    public void setField( int id, int value )
    {
        inv.setField( id, value );
    }

    @Override
    public int getFieldCount()
    {
        return inv.getFieldCount();
    }

    @Override
    public void clear()
    {
        inv.clear();
    }
}
