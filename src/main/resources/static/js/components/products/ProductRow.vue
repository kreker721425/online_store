<template>
        <tr>
            <td>{{ product.id }}</td>
            <td>{{ product.name }}</td>
            <td>{{ product.price }}</td>
            <td>{{ product.img }}</td>
            <td>{{ product.description }}</td>
            <td>{{ product.count }}</td>
            <td>{{ product.manufacturer }}</td>
            <td>{{ product.category }}</td>
            <td>
                <input type="button" value="Изменить" @click="edit" />
                <input type="button" value="Удалить" @click="del" />
            </td>
        </tr>
</template>

<script>
    export default {
        props: ['product', 'editProduct', 'deleteProduct', 'products'],
        methods: {
            edit() {
                this.editProduct(this.product)
            },
            del() {
                this.deleteProduct(this.product)
            }
        }
    }
</script>

<style>

</style>