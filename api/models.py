from django.db import models


class Ship(models.Model):
    name = models.CharField(max_length=30)
    seats = models.IntegerField()

    def __str__(self):
        return self.name


class Tour(models.Model):
    name = models.CharField(max_length=30)
    date = models.DateField()
    time = models.TimeField()
    ship = models.ForeignKey(Ship, related_name='tours')

    def __str__(self):
        return self.name


class Agent(models.Model):
    name = models.CharField(max_length=30)

    def __str__(self):
        return self.name


class Quota(models.Model):
    agent = models.ForeignKey(Agent, related_name='quotas')
    count = models.IntegerField()
    tour = models.ForeignKey(Tour, related_name='quotas')

    def __str__(self):
        return self.agent.name

class Customer(models.Model):
    name = models.CharField(max_length=30)

    def __str__(self):
        return self.name


class Reservation(models.Model):
    count = models.IntegerField()
    tour = models.ForeignKey(Tour, related_name='reservations')
    customer = models.ForeignKey(Customer, related_name='reservations')
