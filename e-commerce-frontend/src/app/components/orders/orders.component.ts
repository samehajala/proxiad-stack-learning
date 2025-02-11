import { Component, OnInit } from '@angular/core';
import { OrderService, Order } from '../../services/order.service';
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {
  orders: Order[] = [];
  orderForm: Order = { customerName: '', product: '', quantity: 1, orderDate: new Date().toISOString().split('T')[0], status: 'Pending' };
  editing: boolean = false;
  private modalInstance: any;

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    this.loadOrders();
  }

  loadOrders(): void {
    this.orderService.getOrders().subscribe(data => {
      this.orders = data;
    });
  }

  openOrderModal(order?: Order): void {
    this.orderForm = order ? { ...order, orderDate: order.orderDate.split('T')[0] } : 
      { customerName: '', product: '', quantity: 1, orderDate: new Date().toISOString().split('T')[0], status: 'Pending' };
    this.editing = !!order;
    this.modalInstance = new bootstrap.Modal(document.getElementById('orderModal')!);
    this.modalInstance.show();
  }

  saveOrder(): void {
    if (this.editing) {
      this.orderService.updateOrder(this.orderForm.id!, this.orderForm).subscribe(() => {
        this.loadOrders();
        this.modalInstance.hide();
      });
    } else {
      this.orderService.createOrder(this.orderForm).subscribe(() => {
        this.loadOrders();
        this.modalInstance.hide();
      });
    }
  }

  deleteOrder(id: number): void {
    if (confirm('Are you sure you want to delete this order?')) {
      this.orderService.deleteOrder(id).subscribe(() => {
        this.loadOrders();
      });
    }
  }
}
