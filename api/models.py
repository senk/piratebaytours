from django.db import models


class Ship(models.Model):
    name = models.CharField(max_length=30)
    seats = models.IntegerField()


class Tour(models.Model):
    name = models.CharField(max_length=30)
    date = models.DateField()
    time = models.TimeField()
    ship = models.ForeignKey(Ship)


class Quota(models.Model):
    count = models.IntegerField()
    tour = models.ForeignKey(Tour)


class Agent(models.Model):
    name = models.CharField(max_length=30)
    quotas = models.ForeignKey(Quota)


class Customer(models.Model):
    name = models.CharField(max_length=30)


class Reservation(models.Model):
    count = models.IntegerField()
    tour = models.ForeignKey(Tour)
    customer = models.ForeignKey(Customer)
